package frc.robot.commands.shooter

import frc.robot.cougartools.CougarCommand

import frc.robot.subsystems.Shooter
import frc.robot.subsystems.BallSystem

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