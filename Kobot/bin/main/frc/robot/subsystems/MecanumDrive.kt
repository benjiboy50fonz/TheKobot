package frc.robot.subsystems

// God, I hope you never use this. In fact, why are you even looking here?!?

import com.ctre.phoenix.motorcontrol.TalonFXInvertType

import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

abstract class MecanumDrive : BaseDrive() {

    private var isFieldOriented = false

    override fun _configureMotors() {
        activeMotors = driveMotors // All motors are active and mecanum.

        if (activeMotors.size != 4) throw Exception("You need four motors in a mecanum system!")

        driveMotors[0].setInverted(TalonFXInvertType.Clockwise) // These two could very well be wrong. Need to some how test it.
        driveMotors[1].setInverted(TalonFXInvertType.Clockwise)
    }

    override fun _calculateSpeeds(x: Double, y: Double, rotate: Double): List<Double> {

        var newX = x
        var newY = y

        if (isFieldOriented) {
            val currentHeading = getAngle() * PI / 180

            val cosA = cos(currentHeading)
            val sinA = sin(currentHeading)

            newX = x * cosA - y * sinA
            newY = x * sinA + y * cosA

        }

        return listOf(
                newX + newY + rotate,
                newX - newY + rotate,
                -newX + newY + rotate,
                -newX - newY + rotate
        )
    }

    fun setFieldOrientation(isFieldCentric: Boolean = true) {
        isFieldOriented = isFieldCentric
    }
}


