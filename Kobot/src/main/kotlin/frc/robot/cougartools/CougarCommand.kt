package frc.robot.cougartools

import edu.wpi.first.wpilibj2.command.CommandBase

import edu.wpi.first.networktables.NetworkTableInstance
import edu.wpi.first.networktables.NetworkTable

abstract class CougarCommand : CommandBase() {

    private val instance = NetworkTableInstance.getDefault()

    // Cougar var ntTables offers a global solution for all the commands.

    open fun makeTable(name: String) { // Omit any '/'s from the "name" argument.
        var table = instance.getTable(name)
        CougarVars.ntTables.add(table)
    }

    open fun addValue(tableName: String, keyName: String, value: Any?) {} // Add this later.

    open fun grabValue(keyName: String): Any? { // Will automatically find it in the table
        for (table in CougarVars.ntTables) {
            if (table.containsKey(keyName)) {
                var entry = table.getEntry(keyName) // Entry might be what I need...
                return (entry.getValue()).getValue()
            }
        }
        return null
    }

    open fun getTableKeys(): Map<NetworkTable, Set<String>> {
        var mappy = mutableMapOf<NetworkTable, Set<String>>()

        for (table in CougarVars.ntTables) {
            mappy[table] = table.getKeys()
        }

        return mappy
    }

    open fun zeroNetworkTableKey(name: String) {}

}