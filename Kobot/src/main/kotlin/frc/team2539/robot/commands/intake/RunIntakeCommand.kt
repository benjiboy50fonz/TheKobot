package frc.team2539.robot.commands.intake

import frc.team2539.robot.cougartools.CougarCommand

import frc.team2539.robot.subsystems.Intake

class RunIntakeCommand : CougarCommand() {
    init {
        addRequirements(Intake)
    }

    override fun initialize() {
        Intake.intake()
    }

    override fun end(interrupted: Boolean) {
        Intake.stopIntake()
    }
}