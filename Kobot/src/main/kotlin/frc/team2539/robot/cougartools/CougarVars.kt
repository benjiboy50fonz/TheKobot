package frc.team2539.robot.cougartools

import edu.wpi.first.networktables.NetworkTable
import edu.wpi.first.networktables.NetworkTableInstance

object CougarVars {

    var ntTables = mutableListOf<NetworkTable>() // A string compilation of all nt table names.

    var instance = NetworkTableInstance.getDefault() // Uses a global instance to call values.

}