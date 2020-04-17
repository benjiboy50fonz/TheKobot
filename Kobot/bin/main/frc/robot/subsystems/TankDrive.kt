package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX

open class TankDrive : SubsystemBase() {

    fun _configureMotors(driveMotors: List<WPI_TalonFX>) {
        var activeMotors = listOf<Any>(driveMotors.get(0), driveMotors.get(1))

        if (driveMotors.size == 4) {
            driveMotors.get(2).follow(driveMotors.get(0))
            driveMotors.get(1).follow(driveMotors.get(3))
        }

    }

    fun _calculateSpeeds(x: Double, y: Double, rotate: Double): List<Double> {
        return listOf<Double>((y + rotate), (-y + rotate))
    }


}