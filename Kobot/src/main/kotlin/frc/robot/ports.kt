/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot

/**
 * This class is for the CAN ports, PWM ports, and the liking. Pretty much the same as Pybot ports,
 * however I  implemented a slightly different layout due to restrictions.
 **/

object Ports {

    object DriveMotors {
        const val FrontLeftMotor = 1
        const val FrontRightMotor = 2
        const val BackLeftMotor = 3
        const val BackRightMotor = 4
    }
}