package frc.team2539.robot

import javax.sound.sampled.Port

object Controls {

    val driverController = LogitechG920(Ports.Controllers.DriverControllerID) //LogitechDualShock(Ports.Controllers.DriverControllerID)
    val operatorController = LogitechDualShock(Ports.Controllers.OperatorControllerID)

}