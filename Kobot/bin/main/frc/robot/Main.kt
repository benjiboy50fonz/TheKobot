/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.Robot;

import edu.wpi.first.wpilibj.RobotBase;

/**
 * Do NOT add any static variables to this class, or any initialization at all.
 * Unless you know what you are doing, do not modify this file except to
 * change the parameter class to the startRobot call.
 */

class Main {
    init {}
    
   /**
    * Main initialization method. Do not perform any initialization here.
    * <p>
    * If you change your main Robot class (name), change the parameter type.
    */

    companion object {
        @JvmStatic 
        fun main(args: Array<String>) = RobotBase.startRobot(::Robot)
    }
}