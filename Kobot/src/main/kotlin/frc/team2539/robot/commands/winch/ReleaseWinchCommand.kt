package frc.team2539.robot.commands.winch

import frc.team2539.robot.cougartools.CougarCommand

import frc.team2539.robot.subsystems.Winch

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