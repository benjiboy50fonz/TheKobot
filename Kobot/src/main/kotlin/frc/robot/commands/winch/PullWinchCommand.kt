package frc.robot.commands.winch

import frc.robot.cougartools.CougarCommand

import frc.robot.subsystems.Winch
import frc.robot.subsystems.LEDs

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