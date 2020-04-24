package frc.team2539.robot.commands.hood

import frc.team2539.robot.cougartools.CougarCommand

import frc.team2539.robot.subsystems.Hood

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