package frc.robot.commands.hood

import frc.robot.cougartools.CougarCommand

import frc.robot.subsystems.Hood

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