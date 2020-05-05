/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team2539.robot

import edu.wpi.first.wpilibj2.command.Command

import frc.team2539.robot.subsystems.DriveTrain

import frc.team2539.robot.commands.intake.RunIntakeCommand
import frc.team2539.robot.commands.intake.RunOutakeCommand
import frc.team2539.robot.commands.intake.FumbleCommand

import frc.team2539.robot.commands.ballsystem.RunBallFlowCommandGroup
import frc.team2539.robot.commands.ballsystem.RunUntilLoadedCommand
import frc.team2539.robot.commands.ballsystem.ClearJamCommand

import frc.team2539.robot.commands.hood.RaiseHoodCommand
import frc.team2539.robot.commands.hood.LowerHoodCommand

import frc.team2539.robot.commands.winch.PullWinchCommand

import frc.team2539.robot.commands.climber.ElevateClimberCommand
import frc.team2539.robot.commands.climber.LowerClimberCommand
import frc.team2539.robot.commands.climber.RaiseClimberCommand

import frc.team2539.robot.commands.limelight.SudoCommandGroup

import frc.team2539.robot.autonomous.Auto

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
    fun configureButtonBindings() { // Basic, most used controls.

        Controls.driverController.A.toggleWhenPressed(RunUntilLoadedCommand())
        Controls.driverController.B.toggleWhenPressed(RunOutakeCommand())

        Controls.driverController.LeftBumper.whileHeld(RaiseHoodCommand())
        Controls.driverController.LeftTrigger.whileHeld(LowerHoodCommand())

        Controls.driverController.RightBumper.whileHeld(ElevateClimberCommand())
        Controls.driverController.RightTrigger.whileHeld(LowerClimberCommand())

        Controls.operatorController.A.toggleWhenPressed(RunBallFlowCommandGroup())
        Controls.operatorController.X.toggleWhenPressed(FumbleCommand())
        Controls.operatorController.B.toggleWhenPressed(ClearJamCommand())

        Controls.operatorController.RightBumper.whileHeld(RaiseHoodCommand())
        Controls.operatorController.RightTrigger.whileHeld(LowerHoodCommand())

        Controls.operatorController.LeftBumper.toggleWhenPressed(SudoCommandGroup())

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
