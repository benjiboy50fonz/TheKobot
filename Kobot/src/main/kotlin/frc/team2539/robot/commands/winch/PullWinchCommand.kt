package frc.team2539.robot.commands.winch

import frc.team2539.robot.cougartools.CougarCommand

import frc.team2539.robot.subsystems.Winch
import frc.team2539.robot.subsystems.LEDs

class PullWinchCommand : CougarCommand() {

    init {
        addRequirements(Winch)
        addRequirements(LEDs)
    }

    override fun initialize() {
        Winch.retractWinch()
        LEDs.rainbowLava()
    }

    override fun end(interrupted: Boolean) {
        Winch.stopWinch()
        LEDs.turnOff()
    }

}