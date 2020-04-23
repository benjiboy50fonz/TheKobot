package frc.robot.subsystems

import edu.wpi.first.wpilibj.DigitalInput

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX
import com.ctre.phoenix.motorcontrol.ControlMode
import com.ctre.phoenix.motorcontrol.NeutralMode
import com.ctre.phoenix.motorcontrol.FeedbackDevice

import frc.robot.Ports
import frc.robot.cougartools.CougarSubsystem

object Turret : CougarSubsystem() {

    private val turretMotor = WPI_TalonSRX(Ports.Turret.TurretMotor).apply {

        config_kP(0, 0.0001, 0)
        config_kI(0, 0.0, 0)
        config_kD(0, 0.001, 0)
        config_kF(0, 0.00019, 0)

        setNeutralMode(NeutralMode.Brake)
        configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder)
        setSelectedSensorPosition(0, 0, 0)

    }

    private val turretLimitSwitch = DigitalInput(Ports.Turret.TurretLimitSwitch)

    private const val min = 0
    private const val max = 1275
    private const val tolerance = 5 // in ticks

    private var fieldAngle = 860.0
    private var speedLimit = 0.0

    fun moveTurret(speed: Double) {
        if ((isZeroed() and (speed > 0)) or ((getPosition() >= max) and (speed < 0))) { stopTurret() }
        else { turretMotor.set(speed) }
    }

    fun stopTurret() { turretMotor.stopMotor() }

    fun givePosition() { turretMotor.setSelectedSensorPosition(1500, 0, 0) }

    fun returnZero() { turretMotor.set(ControlMode.Position, 0.0) }

    fun captureOrientation() { fieldAngle = DriveTrain.getAngle() }

    fun turretFieldOriented() {
        if ((getFieldPosition() > 25.0) and (getFieldPosition() < max - 25.0)) { setPosition(getFieldPosition()) }
        else { stopTurret() }
    }

    fun setPosition(position: Double): Boolean {
        val error = getPosition() - position
        val rotate = error * 0.00075

        fieldMove(rotate)

        return ((getPosition() > position - tolerance) and (getPosition() < position +  tolerance))
    }

    fun getPosition(): Int { return (turretMotor.getSelectedSensorPosition(0)) }

    fun fieldMove(speed: Double) {
        speedLimit = if (getPosition() < max - getPosition()) { getPosition() * 0.0015 }
        else { (max - getPosition()) * 0.0015 }

        speedLimit = if (speedLimit < 0.2) { 0.2 }
        else { speedLimit }

        var newSpeed = speed * speedLimit

        if (((isZeroed()) and (newSpeed > 0.0)) or ((getPosition() >= max) and (newSpeed < 0))) { stopTurret() }
        else { turretMotor.set(newSpeed) }

    }

    fun setZero() { turretMotor.setSelectedSensorPosition(0, 0, 0) }

    fun isZeroed(): Boolean {
        if (!turretLimitSwitch.get()) {
            stopTurret()
            setZero()
            return true
        }

        return false
    }

    fun getFieldPosition(): Double {
        var degrees = DriveTrain.getAngle()

        var ticks = -1 * (degrees * 4096) / 360 + fieldAngle

        if (ticks < 0) {
            ticks += 4096
        } // Keeps positive values . . . probably could've used %
        if (ticks > 4096) {
            ticks -= 4096
        } // Keeps under 4096 ticks.

        return ticks

    }
}

