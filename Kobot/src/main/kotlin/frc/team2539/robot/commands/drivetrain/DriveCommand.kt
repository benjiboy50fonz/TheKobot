package frc.team2539.robot.commands.drivetrain

import frc.team2539.robot.Controls
import frc.team2539.robot.cougartools.CougarCommand
import frc.team2539.robot.subsystems.DriveTrain

class DriveCommand : CougarCommand() {

    init {
        addRequirements(DriveTrain)
    }

    override fun initialize() {
        DriveTrain.stop()
    }

    override fun execute() {
        print("Driving")
        DriveTrain.move(
            y = Controls.driverController.getLeftY(),
            rotate = Controls.driverController.getRightX()
        )
    }

    override fun end(interrupted: Boolean) {
        DriveTrain.stop()
    }

}