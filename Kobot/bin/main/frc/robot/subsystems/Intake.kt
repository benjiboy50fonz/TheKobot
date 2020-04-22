package frc.robot.subsystems

import frc.robot.Ports
import frc.robot.cougartools.CougarSubsystem

import com.revrobotics.CANSparkMax
import com.revrobotics.CANSparkMaxLowLevel.MotorType

object Intake : CougarSubsystem() {

    private val intakeMotor = CANSparkMax(Ports.Intake.IntakeMotor, MotorType.kBrushless).apply {
        inverted = true
    }

    fun intake() {
        intakeMotor.set(0.6)
    }

    fun slowIntake() {
        intakeMotor.set(0.4)
    }

    fun outake() {
        intakeMotor.set(-0.6)
    }

    fun stopIntake() {
        intakeMotor.stopMotor()
    }

}