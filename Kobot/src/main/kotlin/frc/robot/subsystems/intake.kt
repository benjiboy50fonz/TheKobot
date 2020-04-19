package frc.robot.subsystems

import edu.wpi.first.wpilibj2.command.SubsystemBase

import frc.robot.Ports

import com.revrobotics.CANSparkMax
import com.revrobotics.CANSparkMaxLowLevel.MotorType

object Intake : SubsystemBase() {
    private val intakeMotor = CANSparkMax(Ports.Intake.IntakeMotor, MotorType.kBrushless).apply {
        inverted = true
    }

    fun intake() {
        intakeMotor.set(0.6)
    }

    fun outake() {
        intakeMotor.set(-0.6)
    }

    fun slowIntake() {
        intakeMotor.set(0.4)
    }

    fun stopIntake() {
        intakeMotor.stopMotor()
    }

}