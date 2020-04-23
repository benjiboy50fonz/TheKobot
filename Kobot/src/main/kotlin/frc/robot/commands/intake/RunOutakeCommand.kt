package frc.robot.commands.intake

import frc.robot.cougartools.CougarCommand

import frc.robot.subsystems.Intake

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