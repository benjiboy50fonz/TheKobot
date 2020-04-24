package frc.team2539.robot.subsystems

import edu.wpi.first.wpilibj.Timer

import frc.team2539.robot.cougartools.CougarSubsystem

import frc.team2539.robot.Ports

import com.revrobotics.CANSparkMax
import com.revrobotics.CANSparkMaxLowLevel.MotorType

object Climber : CougarSubsystem() {

    private val climberMotor = CANSparkMax(Ports.Climber.ClimberMotor,  MotorType.kBrushless).apply {

        setIdleMode(CANSparkMax.IdleMode.kBrake)
        setInverted(false)
        burnFlash()

    }

    private val timer = Timer()

    fun raiseClimber() {
        climberMotor.set(0.12)
    }

    fun elevateClimber() {
        climberMotor.set(0.25)
    }

    fun lowerClimber() {
        climberMotor.set(-0.4)
    }

    fun stopClimber() {
        climberMotor.stopMotor()
    }

    fun beginTimer() {
        timer.start()
    }

    fun stopTimer() {
        timer.stop()
        timer.reset()
    }


}