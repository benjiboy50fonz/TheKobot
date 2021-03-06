/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team2539.robot

import edu.wpi.first.wpilibj.TimedRobot
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.CommandScheduler

import frc.team2539.robot.subsystems.DriveTrain

import edu.wpi.first.networktables.NetworkTableInstance
import frc.team2539.robot.commands.drivetrain.DriveCommand

/**
 * The VM is configured to automatically run this class, and to call the
 * methods corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
class Robot : TimedRobot() {

    /**
     * This method is run when the robot is first started up and should be used for any
     * initialization code.
     */

    lateinit var autonomousCommand: Command
    lateinit var robotContainer: RobotContainer

    override fun robotInit() {
        // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
        // autonomous chooser on the dashboard.
        var robotContainer = RobotContainer()

        var autonomousCommand = robotContainer.getAutonomousCommand()
    }

    /**
     * This method is called every robot packet, no matter the mode. Use this for items like
     * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
     *
     * <p>This runs after the mode specific periodic functions, but before
     * LiveWindow and SmartDashboard integrated updating.
     */
    override fun robotPeriodic() {
        // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
        // commands, running already-scheduled commands, removing finished or interrupted commands,
        // and running subsystem periodic() methods.  This must be called from the robot's periodic
        // block in order for anything in the Command-based framework to work.
        CommandScheduler.getInstance().run() 
    }

    /**
     * This method is called once each time the robot enters Disabled mode.
     */
    
    override fun disabledInit() {

    }

    override fun disabledPeriodic() {
    
    }

    /**
     * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
     */
    override fun autonomousInit() {

        autonomousCommand = robotContainer.getAutonomousCommand()

        // schedule the autonomous command (example)
        if (autonomousCommand != null)
        {
            autonomousCommand.schedule()
        }
    }

    /**
     * This method is called periodically during autonomous.
     */

    override fun autonomousPeriodic() {

    }

    override fun teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        //if (autonomousCommand != null)
        //{
          //  autonomousCommand.cancel()
        //}
        buildDefaults()

    }

    /**
     * This method is called periodically during operator control.
     */
    override fun teleopPeriodic() {
        println("running")
    }

    override fun testInit() {
        // Cancels all running commands at the start of test mode.
        CommandScheduler.getInstance().cancelAll()
    }

    /**
     * This method is called periodically during test mode.
     */
    override fun testPeriodic() {
        
    }

    fun buildDefaults() {

        DriveTrain.lazyInit()
        DriveTrain.defaultCommand = DriveCommand() // First reference build

    }

}
