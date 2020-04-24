package frc.team2539.robot.commands.climber

import frc.team2539.robot.cougartools.CougarCommand

import frc.team2539.robot.subsystems.Climber

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