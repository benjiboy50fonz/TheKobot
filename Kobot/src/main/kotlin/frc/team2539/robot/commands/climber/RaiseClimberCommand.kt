package frc.team2539.robot.commands.climber

import frc.team2539.robot.cougartools.CougarCommand

import frc.team2539.robot.subsystems.Climber

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