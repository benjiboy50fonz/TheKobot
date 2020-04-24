package frc.team2539.robot.commands.ballsystem

import frc.team2539.robot.cougartools.CougarCommand

import frc.team2539.robot.subsystems.BallSystem

class RunAllCommand : CougarCommand() {

    init{
        addRequirements(BallSystem)
    }

    override fun initialize() {
        BallSystem.runAll()
    }

    override fun end(interrupted: Boolean) {
        BallSystem.stopAll()
    }

}