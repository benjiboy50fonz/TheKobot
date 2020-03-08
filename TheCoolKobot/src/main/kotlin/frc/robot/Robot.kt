/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
class Robot : TimedRobot() {
    lateinit var mAutonomousCommand: Command
    lateinit var mRobotContainer: RobotContainer

    override fun robotInit() {
      mRobotContainer = RobotContainer()

      mAutonomousCommand = mRobotContainer.getAutonomousCommand()

    }

    override fun robotPeriodic() {
      CommandScheduler.getInstance().run()
    }

    override fun disabledInit() {

    }

    override fun autonomousInit() {
      mAutonomousCommand = mRobotContainer.getAutonomousCommand()

    // La scheudler !!! 

      mAutonomousCommand.let { mAutonomousCommand.schedule() }

    }

    override fun teleopInit() {
      mAutonomousCommand.let { mAutonomousCommand.cancel() } // Stops the auto at the end of auto.
    }

    override fun teleopPeriodic() {
      
    }

    override fun testInit() {
      CommandScheduler.getInstance().cancelAll()
    }

    override fun testPeriodic() {
      
    }

}

