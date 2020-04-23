package frc.robot.commands.shooter

import frc.robot.cougartools.CougarCommand

import frc.robot.subsystems.Shooter

class StopShooterCommand : CougarCommand() {

    init {
        addRequirements(Shooter)
    }

    override fun initialize() {
        Shooter.stopShooter()
    }

}