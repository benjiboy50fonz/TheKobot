package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX
import frc.robot.Ports

object BaseDrive : TankDrive() {

    val driveMotors = listOf<WPI_TalonFX>(
                                            WPI_TalonFX(Ports.DriveMotors.FrontLeftMotor),
                                            WPI_TalonFX(Ports.DriveMotors.FrontRightMotor),
                                            WPI_TalonFX(Ports.DriveMotors.BackLeftMotor),
                                            WPI_TalonFX(Ports.DriveMotors.BackRightMotor)
        )

    private var activeMotors: List<WPI_TalonFX> = emptyList()

    init {
        _configureMotors(driveMotors) // Empty list for active motors, later configured.
    }

    fun move(x: Double, y: Double, rotate: Double) {
        var speeds = _calculateSpeeds(x, y, rotate)

        for (motor in (this.activeMotors zip speeds)) motor.get(0).set(ControlMode.PercentOutput, motor.get(1))

    }

    fun stop() {
        for (motor in this.activeMotors) motor.stopMotor()
    }

}