package frc.team2539.robot.commands.hood

import frc.team2539.robot.cougartools.CougarCommand

import frc.team2539.robot.subsystems.Hood

class RaiseHoodCommand : CougarCommand() {

    init {
        addRequirements(Hood)
    }

    override fun initialize() {
        Hood.raiseHood()
    }

    override fun isFinished(): Boolean {
        return (Hood.atHighest())
    }

    override fun end(interrupted: Boolean) {
        Hood.stopHood()
    }

}