package frc.team2539.robot.subsystems

import frc.team2539.robot.cougartools.CougarSubsystem

import kotlin.math.*

object Limelight : CougarSubsystem("limelight") { // Name of limelight nt table

    private const val limelightHeight = 20.0 // Height on robot.
    private const val targetHeight = 90.75 // Height of ideal shooting target.
    private const val calcDistance = 120.0

    private const val height = targetHeight - limelightHeight // Displacement on robot.

    init {
        setLocalTable("limelight") // Call local table functions after this for easy entry reference.
    }

    fun setPipeline(pl: Int) { setValueToLocalTable("pipeline", pl) }

    fun getArea(): Double { return grabValueLocal("ta") as Double } // These doubles will probably need to be 'any's.

    fun getX(): Double { return grabValueLocal("tx") as Double }

    fun getY(): Double { return grabValueLocal("ty") as Double }

    fun getTape(): Boolean { return (grabValueLocal("tv") == 1) }

    fun takeSnapshot() { setValueToLocalTable("snapshot", 1) }

    fun getDistance(): Double { return height / tan(((30.52289 + getY()) * PI) / 180) }
    // The 30.523 is the angle of the ll, relevant to the ground.
    // Divides the height by the tangent of the total angle.

    fun getAreaDistance(): Double { return log(getArea(), 0.992924) + 221.996 } // Logarithmic equation.

    fun onTarget(): Boolean { return (getTape() and (getX() < 0.75)) }

    fun getFieldAngle(): Double { return ((Turret.getFieldPosition() - Turret.getPosition()) * 360) / 4096 + getX() } // Returns degrees.

    fun calcXDistance(): Double { return sin((getFieldAngle() * PI) / 180) * getDistance() } // The PI and 180 put it in radians.

    fun calcYDistance(): Double { return cos((getFieldAngle() * PI) / 180) * getDistance() }

    // God, this file was really painful, and I know I am going to get null errors.

}