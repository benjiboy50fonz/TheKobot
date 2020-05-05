/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team2539.robot

/**
 * This class is for the CAN ports, PWM ports, control IDs, and the liking. Pretty much the same as Pybot ports,
 * however I implemented a slightly different layout due to restrictions.
 **/

object Ports {

    object Controllers {
        const val DriverControllerID = 0
        const val OperatorControllerID = 1
    }

    object DriveMotors {
        const val FrontLeftMotor = 1
        const val FrontRightMotor = 3
        const val BackLeftMotor = 2
        const val BackRightMotor = 4
    }

    object Intake {
        const val IntakeMotor = 13
    }

    object Shooter {
        const val ShooterLeadMotor = 5
        const val ShooterSlaveMotor = 6
    }

    object Turret {
        const val TurretMotor = 8
        const val TurretLimitSwitch = 8 // DI/O
    }

    object Hood {
        const val HoodMotor = 7
        const val HoodTBEncoder = 9 // DI/O
    }

    object BallSystem {
        const val LowerConveyor = 10
        const val VerticalConveyor = 9

        const val ShooterSensor = 2 // DI/O
        const val LowerConveyorSensor = 0 // DI/O
    }

    object LEDSystem {
        const val Controller = 12 // DI/O
    }

    object Climber {
        const val ClimberMotor = 11
    }

    object Winch {
        const val WinchMotor = 14
    }

    object ColorWheel {
        const val ColorWheelSpinnerMotor = 12
        const val RaisingMotor = 15
    }

}