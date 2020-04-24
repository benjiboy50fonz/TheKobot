package frc.team2539.robot.commands.shooter

import frc.team2539.robot.cougartools.CougarCommand

import frc.team2539.robot.subsystems.Shooter

class StopShooterCommand : CougarCommand() {

    init {
        addRequirements(Shooter)
    }

    override fun initialize() {
        Shooter.stopShooter()
    }

}