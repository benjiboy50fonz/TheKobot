package frc.robot.commands.intake

import edu.wpi.first.wpilibj2.command.CommandBase

import frc.robot.subsystems.Intake

class RunOutake : CommandBase() {
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