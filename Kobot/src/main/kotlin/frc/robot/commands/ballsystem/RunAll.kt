package frc.robot.commands.ballsystem

import frc.robot.cougartools.CougarCommand

import frc.robot.subsystems.BallSystem

class RunAll : CougarCommand() {

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