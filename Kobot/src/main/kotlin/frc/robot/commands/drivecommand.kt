package frc.robot.commands

import frc.robot.subsystems.TankDrive

import frc.robot.Layout

import edu.wpi.first.wpilibj2.command.CommandBase

class DriveCommand : CommandBase() {

    init {
        addRequirements(TankDrive)
    }

    override fun initialize() {
        TankDrive.stop()
    }

    override fun execute() {
        TankDrive.move(
            y = Layout.driveController.getRawAxis(1),
            rotate = Layout.driveController.getRawAxis(3)
        )
    }

    override fun end(interrupted: Boolean) {
        TankDrive.stop()
    }

}