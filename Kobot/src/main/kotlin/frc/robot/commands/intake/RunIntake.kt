package frc.robot.commands.intake

import frc.robot.cougartools.CougarCommand

import frc.robot.subsystems.Intake

class RunIntake : CougarCommand() {
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