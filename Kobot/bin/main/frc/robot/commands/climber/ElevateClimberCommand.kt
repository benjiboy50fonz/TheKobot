package frc.robot.commands.climber

import frc.robot.cougartools.CougarCommand

import frc.robot.subsystems.Climber

class ElevateClimberCommand : CougarCommand() {

    init {
        addRequirements(Climber)
    }

    override fun initialize() {
        Climber.elevateClimber()
    }

    override fun end(interrupted: Boolean) {
        Climber.stopClimber()
    }

}