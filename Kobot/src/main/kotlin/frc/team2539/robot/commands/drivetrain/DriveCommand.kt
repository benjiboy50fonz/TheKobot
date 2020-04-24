package frc.team2539.robot.commands.drivetrain

import frc.team2539.robot.subsystems.DriveTrain

import frc.team2539.robot.Controls
import frc.team2539.robot.cougartools.CougarCommand

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