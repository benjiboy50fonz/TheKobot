package frc.robot.commands.climber

import frc.robot.cougartools.CougarCommand

import frc.robot.subsystems.Climber

class LowerClimberCommand : CougarCommand() {

    init {
        addRequirements(Climber)
    }

    override fun initialize() {
        Climber.lowerClimber()
    }

    override fun end(interrupted: Boolean) {
        Climber.stopClimber()
    }

}