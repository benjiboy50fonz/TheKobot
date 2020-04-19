package frc.robot.commands.drivetrain

import frc.robot.subsystems.DriveTrain

import edu.wpi.first.wpilibj2.command.CommandBase
import frc.robot.Controls

class DriveCommand : CommandBase() {

    init {
        addRequirements(DriveTrain)
    }

    override fun initialize() {
        DriveTrain.stop()
    }

    override fun execute() {
        DriveTrain.move(
            y = Controls.driverController.getRawAxis(1),
            rotate = Controls.driverController.getRawAxis(3)
        )
    }

    override fun end(interrupted: Boolean) {
        DriveTrain.stop()
    }

}