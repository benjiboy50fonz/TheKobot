package frc.robot.subsystems

import com.revrobotics.CANSparkMax
import com.revrobotics.CANSparkMaxLowLevel.MotorType
import com.revrobotics.ControlType

import frc.robot.Ports

import frc.robot.cougartools.CougarSubsystem

object ColorWheel : CougarSubsystem() {

    private val colors = listOf<String>("y", "r", "g", "b", "y", "r", "g", "b")

    private val colorWheelMotor = CANSparkMax(Ports.ColorWheel.ColorWheelSpinnerMotor, MotorType.kBrushless).apply {

        setInverted(false)

    }

    private val colorWheelMotorEnc = colorWheelMotor.getEncoder()

    private val colorWheelMotorPIDCont = colorWheelMotor.getPIDController().apply {

        setP(0.001, 0)
        setI(0.0, 0)
        setD(0.0, 0)
        setIZone(0.0, 0)
        setFF(0.0, 0)

    }

    private val colorWheelRaiserMotor = CANSparkMax(Ports.ColorWheel.RaisingMotor,  MotorType.kBrushed)

    private const val upPositon = 135
    private const val downPosition = 0

    init {
        colorWheelMotor.burnFlash()
    }

    fun resetEncoder() {
        colorWheelMotorEnc.setPosition(0.0)
    }

    fun autoSpinWheel(rotations: Double = 675.0) {
        resetEncoder()
        colorWheelMotorPIDCont.setReference(rotations, ControlType.kPosition, 0, 0.0)
    }

    fun stopColorWheelMotor() {
        colorWheelMotor.stopMotor()
    }

    fun getEncPosition(): Double {
        return colorWheelMotorEnc.getPosition()
    }

    fun spinClockwise() {
        colorWheelMotor.set(0.2)
    }

    fun spinCounterClockwise() {
        colorWheelMotor.set(-0.2)
    }

    fun raiseArm() {
        colorWheelRaiserMotor.set(0.8)
    }

    fun lowerArm() {
        colorWheelRaiserMotor.set(-0.8)
    }

    fun stopArmMotor() {
        colorWheelRaiserMotor.stopMotor()
    }

    fun getAmperage(): Double {
        return colorWheelRaiserMotor.getOutputCurrent()
    }

    fun stopOnImpact(): Boolean {
        return (colorWheelRaiserMotor.getOutputCurrent() >= 13.0)
    }

}