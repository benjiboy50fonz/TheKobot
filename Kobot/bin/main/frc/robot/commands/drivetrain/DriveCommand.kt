package frc.robot.commands.drivetrain

import frc.robot.subsystems.DriveTrain

import frc.robot.Controls
import frc.robot.cougartools.CougarCommand

class DriveCommand : CougarCommand() {

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