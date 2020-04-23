package frc.robot.commands.ballsystem

import frc.robot.cougartools.CougarCommand

import frc.robot.subsystems.BallSystem

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