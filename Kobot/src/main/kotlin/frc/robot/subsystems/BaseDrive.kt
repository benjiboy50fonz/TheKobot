package frc.robot.subsystems

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX
import com.ctre.phoenix.motorcontrol.TalonFXControlMode

import edu.wpi.first.wpilibj2.command.SubsystemBase

import frc.robot.Ports

abstract class BaseDrive : SubsystemBase() {

    open val driveMotors = listOf(
            WPI_TalonFX(Ports.DriveMotors.FrontLeftMotor),
            WPI_TalonFX(Ports.DriveMotors.FrontRightMotor),
            WPI_TalonFX(Ports.DriveMotors.BackLeftMotor),
            WPI_TalonFX(Ports.DriveMotors.BackRightMotor)
    )

    open var activeMotors = emptyList<WPI_TalonFX>()

    init {
        _configureMotors() // Empty list for active motors, later configured.
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
        return 90.0
    }

    open fun _configureMotors() {
        throw NotImplementedError("Well this ain't Python.")
    }

    open fun _calculateSpeeds(x: Double, y: Double, rotate: Double): List<Double> {
        throw NotImplementedError("Well this ain't Python.")
        return listOf(0.0, 0.0)
    }
}