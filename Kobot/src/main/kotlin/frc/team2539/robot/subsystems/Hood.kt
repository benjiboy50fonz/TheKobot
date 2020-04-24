package frc.team2539.robot.subsystems

import edu.wpi.first.wpilibj.DigitalInput
import edu.wpi.first.wpilibj.DutyCycle

import com.revrobotics.CANSparkMax
import com.revrobotics.CANSparkMaxLowLevel.MotorType

import frc.team2539.robot.Ports
import frc.team2539.robot.cougartools.CougarSubsystem

import kotlin.math.abs

object Hood : CougarSubsystem() {

    private val hoodMotor = CANSparkMax(Ports.Hood.HoodMotor, MotorType.kBrushless)

    private val hoodEncoder = hoodMotor.getEncoder()

    private val hoodPIDCont = hoodMotor.getPIDController()

    private val hoodTBEncoder = DutyCycle(DigitalInput(Ports.Hood.HoodTBEncoder))

    private var dir = "u"

    private var setSpeed = 0.3

    private const val angleMax = 236.00

    private const val angleMin = 166.00

    private var llHoodTuner = 13.0

    fun getPosition(): Double { return hoodTBEncoder.getOutput() * 360 }

    fun upLLHood() { llHoodTuner += 0.1 }

    fun downLLHood() { llHoodTuner -= 0.1 }

    fun stopHood() { hoodMotor.stopMotor() }

    fun setPercent(speed: Double) { hoodMotor.set(speed) }

    fun raiseHood() {
        if (getPosition() < angleMax) { hoodMotor.set(0.1) }
        else { stopHood() }
    }

    fun lowerHood() {
        if (getPosition() > angleMin) { hoodMotor.set(-0.1) }
        else { stopHood() }
    }

    fun atHighest(): Boolean {
        if (getPosition() >= angleMax) {
            stopHood()
            return true
        }
        return false
    }

    fun atLowest(): Boolean {
        if (getPosition() <= angleMin) {
            stopHood()
            return true
        }
        return false
    }

    fun openLoopSetPosition(angle: Double) {
        if (abs(getPosition() - angle) >= 2.0) { setPercent(0.005 * (angle - getPosition())) }
        else { stopHood() }

    }

    fun setShootAngle(angle: Double) {
        var targetPos = angleMax - 2 * (angle - 8.84)
        var error = -1 * (getPosition() - targetPos)
        if ((angleMin < targetPos) and (targetPos < angleMax)) {
            if (abs(error) < 0.1) { stopHood() }
            else {
                var speed = error * 0.01
                speed = if (abs(speed) > 0.5) { kotlin.math.sign(speed) * 0.5 } else { speed }
                setPercent(speed)
            }
        }
    }

    fun setAngle(angle: Double) {
        var targetPos = 260 - (2 * angle)
        var error = -1 * (getPosition() - targetPos)
        if ((angleMin <  targetPos) and (targetPos < angleMax)) {
            if (abs(error) < 0.1) { stopHood() }
            else {
                var speed = error * 0.03
                speed = if (abs(speed) > 0.5) { kotlin.math.sign(speed) * 0.5 } else { speed }
                setPercent(speed)
            }
        }
    }

    fun getLLHoodTuner(): Double { return llHoodTuner }

}