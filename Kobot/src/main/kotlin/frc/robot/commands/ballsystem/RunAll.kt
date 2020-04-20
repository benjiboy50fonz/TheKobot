package frc.robot.commands.ballsystem

import edu.wpi.first.wpilibj2.command.CommandBase

import frc.robot.subsystems.BallSystem

class RunAll : CommandBase() {

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