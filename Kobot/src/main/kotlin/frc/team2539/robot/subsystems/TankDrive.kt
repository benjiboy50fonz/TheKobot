package frc.team2539.robot.subsystems

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX

abstract class TankDrive : BaseDrive() {

    override fun _configureMotors() {
        print("CONFIGURED")
        activeMotors = listOf(driveMotors[0], driveMotors[1])

        if (driveMotors.size == 4) {
            println("FOur")
            driveMotors[2].follow(driveMotors[0])
            driveMotors[3].follow(driveMotors[1])
        }

        for (motor in activeMotors) {
            motor.setSensorPhase(true)
        }

    }

    override fun _calculateSpeeds(x: Double, y: Double, rotate: Double): List<Double> {
        return listOf((y + rotate), (-y + rotate))
    }
}