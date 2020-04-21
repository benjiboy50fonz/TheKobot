package frc.robot.subsystems

import com.revrobotics.CANSparkMax
import com.revrobotics.CANSparkMaxLowLevel.MotorType

import frc.robot.Ports
import frc.robot.cougartools.CougarSubsystem

object Winch : CougarSubsystem() {

    private val winchMotor = CANSparkMax(Ports.Winch.WinchMotor, MotorType.kBrushless).apply {

        setIdleMode(CANSparkMax.IdleMode.kBrake)
        setInverted(false)

    }

    fun retractWinch() {
        winchMotor.set(0.8)
    }

    fun slowRetract() {
        winchMotor.set(0.4)
    }

    fun loosenWinch() {
        winchMotor.set(-0.8) // Not a good idea if it's ratcheted!
    }

    fun stopWinch() {
        winchMotor.stopMotor()
    }

}