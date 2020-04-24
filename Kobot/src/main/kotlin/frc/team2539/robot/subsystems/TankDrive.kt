package frc.team2539.robot.subsystems

abstract class TankDrive : BaseDrive() {

    override fun _configureMotors() {
        activeMotors = listOf(driveMotors[0], driveMotors[1])

        if (driveMotors.size == 4) {
            driveMotors[2].follow(driveMotors[0])
            driveMotors[1].follow(driveMotors[3])
        }

        for (motor in activeMotors) {
            motor.setSensorPhase(true)
        }

    }

    override fun _calculateSpeeds(x: Double, y: Double, rotate: Double): List<Double> {
        return listOf((y + rotate), (-y + rotate))
    }
}