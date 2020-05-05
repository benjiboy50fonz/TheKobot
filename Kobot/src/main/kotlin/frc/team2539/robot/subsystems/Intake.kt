package frc.team2539.robot.subsystems

import frc.team2539.robot.Ports
import frc.team2539.robot.cougartools.CougarSubsystem

import com.revrobotics.CANSparkMax
import com.revrobotics.CANSparkMaxLowLevel.MotorType

object Intake : CougarSubsystem("Intake") {

    var fumbleDirection: Boolean = true

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

    fun fumbleReverse() {
        intakeMotor.set(-0.35)
    }

    fun fumbleForward() {
        intakeMotor.set(1.0)
    }

    fun fumble() { // Call every X seconds
        when(fumbleDirection) {
            true -> fumbleReverse()
            false -> fumbleForward()
        }

        fumbleDirection = !fumbleDirection // Inverts it

    }

}