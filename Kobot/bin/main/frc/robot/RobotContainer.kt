/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot

import edu.wpi.first.wpilibj2.command.Command

import frc.robot.commands.intake.RunIntake
import frc.robot.commands.intake.RunOutake

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser

import frc.robot.autonomous.Auto

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
class RobotContainer 
{
    // The robot's subsystems and commands are defined here...

    val autonomousCommand = Auto()

    /**
     * The container for the robot.  Contains subsystems, OI devices, and commands.
     */
    init {
        // Configure the button bindings
        configureButtonBindings()

    }

    /**
     * Use this method to define your button->command mappings.  Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick Joystick} or {@link XboxController}), and then passing it to a
     * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton JoystickButton}.
     */
    fun configureButtonBindings() {
        Controls.driverController.A.toggleWhenPressed(RunIntake())
        Controls.driverController.B.toggleWhenPressed(RunOutake())

        Controls.operatorController.A.toggleWhenPressed(RunIntake())
        Controls.operatorController.B.toggleWhenPressed(RunOutake())

    }


    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    fun getAutonomousCommand(): Command {
        // An ExampleCommand will run in autonomous
        return autonomousCommand
    }
}
