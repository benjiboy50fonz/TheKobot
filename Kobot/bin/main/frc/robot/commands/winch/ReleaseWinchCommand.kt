package frc.robot.commands.winch

import frc.robot.cougartools.CougarCommand

import frc.robot.subsystems.Winch

class ReleaseWinchCommand : CougarCommand() {

    init {
        addRequirements(Winch)
    }

    override fun initialize() {
        Winch.loosenWinch()
    }

    override fun end(interrupted: Boolean) {
        Winch.stopWinch()
    }

}