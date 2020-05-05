package frc.team2539.robot.cougartools

import com.revrobotics.CANSparkMax
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX

import edu.wpi.first.networktables.NetworkTable
import edu.wpi.first.networktables.NetworkTableInstance

import edu.wpi.first.wpilibj2.command.SubsystemBase

import kotlinx.coroutines.*

abstract class CougarSubsystem(name: String = "") : SubsystemBase() {

    // A special, FRC 2539 parent class that will utilize coroutines.

    open var localTableName: String = name // NOTE: Incredibly important that this is overridden in the creation of a subsystem.

    open lateinit var myLocalTable: NetworkTable

    open var defaultSlot: Int = 0

    open var pidSets = mutableListOf<List<Double>>(mutableListOf(0.1, 0.0, 0.0, 0.0, 0.0))

    init {
        setLocalTable(localTableName)
    }

    // Cougar var ntTables offers a global solution for all the commands.

    open fun lazyInit() {println("Init'd")} // Inits objects for defaults because of stupid lazy initialization lol.

    open fun addPIDSet(slot: Int, p: Double, i: Double, d: Double, ff: Double, iz: Double) {
        pidSets.add(slot, listOf<Double>(p, i, d, ff, iz))
    }

    open fun setDefaultPIDSlot(newSlot: Int) { defaultSlot = newSlot}

    open fun getDefaultPIDSlot(): Int { return defaultSlot }

    open fun getPIDSets(): List<List<Double>> { return pidSets } // For debugging purposes.

    fun setLocalTable(name: String) {
        localTableName = name
        myLocalTable = CougarVars.instance.getTable(localTableName)
        if (myLocalTable !in CougarVars.ntTables) {
            CougarVars.ntTables.add(myLocalTable)
        }
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

    open fun applyPIDFromLocal(motor: Any?, pN: String, iN: String,
                      dN: String, ffN: String, izN: String)
    { // The xN's are the key names for the gains or values.

        when (motor) {
            is CANSparkMax -> setSparkMAXPIDs(motor, pN, iN, dN, ffN, izN)
            is WPI_TalonSRX -> setTalonSRXPIDs(motor, pN, iN, dN, ffN, izN)
            is WPI_TalonFX -> setTalonFXPIDs(motor, pN, iN, dN, ffN, izN)
        }
    }

    private fun setTalonFXPIDs(motor: WPI_TalonFX, p: String, i: String, d: String, ff: String, iz: String) {
        motor.apply {
            config_kP(0, myLocalTable.getEntry(p).getDouble(pidSets[defaultSlot][0]), 0)
            config_kI(0, myLocalTable.getEntry(i).getDouble(pidSets[defaultSlot][1]), 0)
            config_kD(0, myLocalTable.getEntry(d).getDouble(pidSets[defaultSlot][2]), 0)
            config_kF(0, myLocalTable.getEntry(ff).getDouble(pidSets[defaultSlot][3]), 0)
            config_IntegralZone(0, myLocalTable.getEntry(iz).getDouble(pidSets[defaultSlot][4]).toInt(), 0)
        }
    }

    private fun setTalonSRXPIDs(motor: WPI_TalonSRX, p: String, i: String, d: String, ff: String, iz: String) {
        motor.apply {
            config_kP(0, myLocalTable.getEntry(p).getDouble(pidSets[defaultSlot][0]), 0)
            config_kI(0, myLocalTable.getEntry(i).getDouble(pidSets[defaultSlot][1]), 0)
            config_kD(0, myLocalTable.getEntry(d).getDouble(pidSets[defaultSlot][2]), 0)
            config_kF(0, myLocalTable.getEntry(ff).getDouble(pidSets[defaultSlot][3]), 0)
            config_IntegralZone(0, myLocalTable.getEntry(iz).getDouble(pidSets[defaultSlot][4]).toInt(), 0)
        }
    }

    private fun setSparkMAXPIDs(motor: CANSparkMax, p: String, i: String, d: String, ff: String, iz: String) {
        motor.getPIDController().apply {
            setP(myLocalTable.getEntry(p).getDouble(pidSets[defaultSlot][0])) // Sets the defaults as the first values in pidSets.
            setI(myLocalTable.getEntry(i).getDouble(pidSets[defaultSlot][1]))
            setD(myLocalTable.getEntry(d).getDouble(pidSets[defaultSlot][2]))
            setFF(myLocalTable.getEntry(ff).getDouble(pidSets[defaultSlot][3]))
            setIZone(myLocalTable.getEntry(iz).getDouble(pidSets[defaultSlot][4]))
        }
    }

    open fun makeTable(name: String) { // Omit any '/'s from the "name" argument.
        var table = CougarVars.instance.getTable(name)
        CougarVars.ntTables.add(table)
    }

    open fun setValueToLocalTable(keyName: String, value: Any?) {
        myLocalTable.getEntry(keyName).setValue(value)
    }

    open fun setValueToExternalTable(keyName: String, tableName: String, value: Any?) {
        CougarVars.instance.getTable(tableName).getEntry(keyName).setValue(value)
    }

    open fun deleteEntryInLocalTable(keyName: String) {
        myLocalTable.getEntry(keyName).delete()
    }

    open fun grabValueLocal(keyName: String): Any? { // Will automatically find it in the local table
        try { return myLocalTable.getEntry(keyName).getValue() }
        catch(e: Exception) { throw Error("Key '$keyName' is not in the local table, the '$localTableName' table!") }
    }

    open fun grabValueExternal(keyName: String): Any? { // Only use this when not referencing the home table.
        for (table in CougarVars.ntTables) {
            if (table.containsKey(keyName)) {
                return table.getEntry(keyName).getValue()
            }
        }

        throw Error("Key '$keyName' could not be found in of the globally established tables. ")
    }

    open fun getTableKeys(tableName: String): MutableSet<String>? {
        return CougarVars.instance.getTable(tableName).getKeys()
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