package frc.robot.commands.hood

import frc.robot.cougartools.CougarCommand

import frc.robot.subsystems.Hood

class LowerHoodCommand : CougarCommand() {

    init {
        addRequirements(Hood)
    }

    override fun initialize() {
        Hood.lowerHood()
    }

    override fun isFinished(): Boolean {
        return (Hood.atLowest())
    }

    override fun end(interrupted: Boolean) {
        Hood.stopHood()
    }

}