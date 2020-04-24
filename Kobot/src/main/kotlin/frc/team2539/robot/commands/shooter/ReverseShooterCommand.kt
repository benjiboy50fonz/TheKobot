package frc.team2539.robot.commands.shooter

import frc.team2539.robot.cougartools.CougarCommand

import frc.team2539.robot.subsystems.Shooter
import frc.team2539.robot.subsystems.BallSystem

class ReverseShooterCommand : CougarCommand() {

    init {
        addRequirements(Shooter)
        addRequirements(BallSystem)
    }

    override fun initialize() {
        BallSystem.reverseAll()
        Shooter.reverseShooter()
    }

    override fun end(interrupted: Boolean) {
        BallSystem.stopAll()
        Shooter.stopShooter()
    }

}