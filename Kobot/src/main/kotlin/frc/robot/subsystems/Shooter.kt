package frc.robot.subsystems

import kotlin.math.abs

import com.revrobotics.CANSparkMax
import com.revrobotics.CANSparkMaxLowLevel.MotorType
import com.revrobotics.ControlType

import frc.robot.Ports
import frc.robot.cougartools.CougarSubsystem

object Shooter : CougarSubsystem() {

    private val shooterLeadMotor = CANSparkMax(Ports.Shooter.ShooterLeadMotor, MotorType.kBrushless).apply {

        setIdleMode(CANSparkMax.IdleMode.kBrake)
        setInverted(false) // Does not invert it to achieve the correct shooting direction.

    }

    private val shooterLeadEnc = shooterLeadMotor.getEncoder()

    private val shooterLeadPIDCont = shooterLeadMotor.getPIDController().apply {
        this.setP(0.0015, 0)
        this.setI(0.0, 0)
        this.setD(0.0, 0)
        this.setFF(0.000162, 0)
        this.setIZone(0.0, 0)
    }

    private val shooterSlaveMotor = CANSparkMax(Ports.Shooter.ShooterSlaveMotor, MotorType.kBrushless).apply {

        setIdleMode(CANSparkMax.IdleMode.kBrake)
        follow(shooterLeadMotor, true) // Tells the second, slave motor to follow and inverse so they cooperate.

    }

    private val shooterSlaveEnc = shooterSlaveMotor.getEncoder()

    private val shooterSlavePIDCont = shooterSlaveMotor.getPIDController().apply {
        this.setP(0.0015, 0)
        this.setI(0.0, 0)
        this.setD(0.0, 0)
        this.setFF(0.000162, 0)
        this.setIZone(0.0, 0)
    }

    private var goal: Double = 0.0
    private var rpmTolerance: Double = 50.0

    fun reverseShooter() {
        shooterLeadMotor.set(-0.7)
    }

    fun stopShooter() {
        shooterLeadMotor.stopMotor()
        goal = 0.0
    }

    fun getRPM(): Double {
        return (shooterLeadEnc.getVelocity() + shooterSlaveEnc.getVelocity()) / 2
    }

    fun setRPM(rpm: Double) {
        shooterLeadPIDCont.setReference(rpm, ControlType.kVelocity, 0, 0.0)
        goal = rpm
    }

    fun atRPM(): Boolean {
        return (abs(goal - getRPM()) <= rpmTolerance)
    }

}