package frc.robot.commands

import frc.robot.subsystems.DriveTrain

import frc.robot.Layout

import edu.wpi.first.wpilibj2.command.CommandBase

class DriveCommand : CommandBase() {

    init {
        addRequirements(DriveTrain)
    }

    override fun initialize() {
        DriveTrain.stop()
    }

    override fun execute() {
        DriveTrain.move(
            y = Layout.driveController.getRawAxis(1),
            rotate = Layout.driveController.getRawAxis(3)
        )
    }

    override fun end(interrupted: Boolean) {
        DriveTrain.stop()
    }

}