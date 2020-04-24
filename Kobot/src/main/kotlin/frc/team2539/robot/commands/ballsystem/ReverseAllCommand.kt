package frc.team2539.robot.commands.ballsystem

import frc.team2539.robot.cougartools.CougarCommand

import frc.team2539.robot.subsystems.BallSystem

class ReverseAllCommand : CougarCommand() {

    init{
        addRequirements(BallSystem)
    }

    override fun initialize() {
        BallSystem.reverseAll()
    }

    override fun end(interrupted: Boolean) {
        BallSystem.stopAll()
    }

}