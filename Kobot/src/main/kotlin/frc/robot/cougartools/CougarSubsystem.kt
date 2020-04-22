package frc.robot.cougartools

import com.revrobotics.CANSparkMax

import edu.wpi.first.networktables.NetworkTable
import edu.wpi.first.networktables.NetworkTableInstance

import edu.wpi.first.wpilibj2.command.SubsystemBase

import kotlinx.coroutines.*

abstract class CougarSubsystem : SubsystemBase() {

    // A special, FRC 2539 parent class that will utilize coroutines.

    private val instance = NetworkTableInstance.getDefault() // Global NT Instance

    open var localTableName: String = "" // NOTE: Incredibly important that this is overridden in the creation of a subsystem.

    open lateinit var myLocalTable: NetworkTable

    // Cougar var ntTables offers a global solution for all the commands.

    open fun setLocalTable(name: String) {
        localTableName = name
        myLocalTable = instance.getTable(localTableName)
    }

    open fun doWhileRunning(_delayingTask: () -> Unit, _waitingTask: () -> Unit) = runBlocking {

        /* Offers the ability to do "_waitingTask", while "_delayingTask" is finishing. Neither can
        *  currently take arguments however. Delaying task should take longer!
        */

        val myJob = launch {
            _delayingTask()
        }
        _waitingTask()
        myJob.join() // Waits for myJob to finish.

        // Better to use delay(), doesn't block threads. Just a side note :).

    }

    open fun doWhileRunningWithTimeout(_delayingTask: () -> Unit, _waitingTask: () -> Unit, timeOut: Double) = runBlocking {

        /* Offers the ability to run two tasks, and if one finishes, a time left for the other begins! Still no parameters.
        *  timeOut is the delay before cancel in SECONDS. Again, waiting is the shorter task.
        */

        val alotted = timeOut * 1000

        val myJob = launch {
            _delayingTask()
        }
        _waitingTask()

        delay(alotted.toLong())

        myJob.cancelAndJoin()

        // I honestly have no idea if this is gonna work, so gonna need a lot of debugging.

    }

    open fun pushPIDs(motor: Any?) {
        when (motor) {
            is CANSparkMax -> publishSparkMAXPIDs(motor)
        }
    }

    private fun publishSparkMAXPIDs(motor: CANSparkMax) {

    }

    open fun makeTable(name: String) { // Omit any '/'s from the "name" argument.
        var table = instance.getTable(name)
        CougarVars.ntTables.add(table)
    }

    open fun addValueToLocalTable(keyName: String, value: Any?) {
        when (value) {
            is String -> myLocalTable.getEntry(keyName).setString(value)
            is Int -> myLocalTable.getEntry(keyName).setDouble(value.toDouble())
            is Double -> myLocalTable.getEntry(keyName).setDouble(value)
            is Boolean -> myLocalTable.getEntry(keyName).setBoolean(value)
        }
    }

    open fun deleteEntryInLocalTable(keyName: String) {
        myLocalTable.getEntry(keyName).delete()
    }

    open fun grabValueLocalTable(keyName: String): Any? { // Will automatically find it in the local table
        if (myLocalTable.containsKey(keyName)) {
            return myLocalTable.getEntry(keyName)
        }
        throw Error("Key '$keyName' is not in the local table, the '$localTableName' table!")
    }

    open fun grabValueExternalTable(keyName: String): Any? { // Only use this when not referencing the home table.
        for (table in CougarVars.ntTables) {
            if (table.containsKey(keyName)) {
                return table.getEntry(keyName)
            }
        }

        throw Error("Key '$keyName' could not be found in of the globally established tables. ")
    }

    open fun getTableKeys(tableName: String): MutableSet<String>? {
        return instance.getTable(tableName).getKeys()
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