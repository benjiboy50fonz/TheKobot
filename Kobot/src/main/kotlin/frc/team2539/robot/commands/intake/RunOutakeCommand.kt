package frc.team2539.robot.commands.intake

import frc.team2539.robot.cougartools.CougarCommand

import frc.team2539.robot.subsystems.Intake

class RunOutakeCommand : CougarCommand() {
    init {
        addRequirements(Intake)
    }

    override fun initialize() {
        Intake.outake()
    }

    override fun end(interrupted: Boolean) {
        Intake.stopIntake()
    }
}