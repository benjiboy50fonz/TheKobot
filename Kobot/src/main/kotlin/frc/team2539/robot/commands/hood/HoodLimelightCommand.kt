package frc.team2539.robot.commands.hood

import frc.team2539.robot.cougartools.CougarCommand

import frc.team2539.robot.subsystems.Hood
import frc.team2539.robot.subsystems.Limelight
import kotlin.math.pow

class HoodLimelightCommand : CougarCommand() {

    init {
        addRequirements(Hood)
    }

    override fun initialize() {
        Limelight.setPipeline(1)
    }

    override fun execute() {
        if (Limelight.getTape()) {
            if (Limelight.getArea() > 1.289) { Hood.setShootAngle(1.76491 * Limelight.getArea().pow(2) + 14) }
            else { Hood.setShootAngle(1.76491 * Limelight.getArea().pow(2) + 11.5917) }
        }
        else { Hood.stopHood() }
    }

    override fun end(interrupted: Boolean) {
        Hood.stopHood()
        Limelight.setPipeline(0)
    }

}