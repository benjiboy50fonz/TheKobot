package frc.robot.commands.climber

import frc.robot.cougartools.CougarCommand

import frc.robot.subsystems.Climber

class RaiseClimberCommand : CougarCommand() {

    init {
        addRequirements(Climber)
    }

    override fun initialize() {
        Climber.raiseClimber()
    }

    override fun end(interrupted: Boolean) {
        Climber.stopClimber()
    }

}