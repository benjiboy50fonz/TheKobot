package frc.team2539.robot.subsystems

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX
import com.ctre.phoenix.motorcontrol.TalonFXControlMode

import com.kauailabs.navx.frc.AHRS

import frc.team2539.robot.Ports

import frc.team2539.robot.commands.drivetrain.DriveCommand

import frc.team2539.robot.cougartools.CougarSubsystem

abstract class BaseDrive : CougarSubsystem() {

    open val driveMotors = listOf(
            WPI_TalonFX(Ports.DriveMotors.FrontLeftMotor),
            WPI_TalonFX(Ports.DriveMotors.FrontRightMotor),
            WPI_TalonFX(Ports.DriveMotors.BackLeftMotor),
            WPI_TalonFX(Ports.DriveMotors.BackRightMotor)
    )

    open var activeMotors = emptyList<WPI_TalonFX>()

    private val navX = AHRS().apply { calibrate() }

    private val flatAngle = 0

    init {
        this._configureMotors() // Empty list for active motors, later configured.

        for (motor in activeMotors) { // Configure PIDs
            applyPIDFromLocal(motor, "P", "I", "D", "FF", "IZone") // Applies PIDs from the dashboard inputs with these keys
        }

        navX.reset()
        setLocalTable("DriveTrain")

    }

    fun move(x: Double = 0.0, y: Double, rotate: Double) {
        val speeds = _calculateSpeeds(x, y, rotate).toList()

        for (motor in (activeMotors zip speeds)) { // Motor is not a list, but a pair. call motor.toList()... in order to use list stuff.
            motor.first.set(TalonFXControlMode.PercentOutput, motor.second)
        }
    }

    fun stop() {
        for (motor in activeMotors) motor.stopMotor()
    }

    fun getAngle(): Double {
        return navX.angle // I'm guessing this is right...
    }

    fun resetAngle() {
        navX.reset()
    }

    fun getAngleTo(angle: Double): Double {
        return (angle - getAngle())
    }

    fun getTilt(): Float {
        return navX.pitch
    }

    open fun _configureMotors() {
        throw NotImplementedError("Well this ain't Python.")
    }

    open fun _calculateSpeeds(x: Double, y: Double, rotate: Double): List<Double> {
        throw NotImplementedError("Well this ain't Python.")
        return listOf(0.0, 0.0)
    }

    init {
        defaultCommand = DriveCommand()
    }

}