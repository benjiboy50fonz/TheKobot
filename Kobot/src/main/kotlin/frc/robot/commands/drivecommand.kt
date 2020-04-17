package frc.robot.commands;

import frc.robot.subsystems.BaseDrive;

import frc.robot.Layout;

import edu.wpi.first.wpilibj2.command.CommandBase;

class DriveCommand : CommandBase() {
    init {
        addRequirements(BaseDrive)
    }

    override fun initialize() {
        BaseDrive.stop()
    }

    override fun execute() {
        //TankDrive.move(0.2, 3.1, 4.1)
            //Layout.driveController.getRawAxis(1),
           // Layout.driveController.getRawAxis(3)
        //)
    }

}