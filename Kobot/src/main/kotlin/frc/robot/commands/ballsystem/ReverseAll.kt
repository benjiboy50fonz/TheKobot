package frc.robot.commands.ballsystem

import edu.wpi.first.wpilibj2.command.CommandBase

import frc.robot.subsystems.BallSystem

class ReverseAll : CommandBase() {

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