package frc.robot.subsystems

object TankDrive : BaseDrive() {

    override fun _configureMotors() {
        activeMotors = listOf(driveMotors[0], driveMotors[1])

        if (driveMotors.size == 4) {
            driveMotors[2].follow(driveMotors[0])
            driveMotors[1].follow(driveMotors[3])
        }

    }

    override fun _calculateSpeeds(x: Double, y: Double, rotate: Double): Pair<Double, Double> {
        return Pair(first = (y + rotate), second = (-y + rotate))
    }
}