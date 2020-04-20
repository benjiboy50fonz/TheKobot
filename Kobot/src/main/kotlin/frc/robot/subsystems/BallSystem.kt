package frc.robot.subsystems

import edu.wpi.first.wpilibj2.command.SubsystemBase
import edu.wpi.first.wpilibj.DigitalInput

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX
import com.ctre.phoenix.motorcontrol.ControlMode
import com.ctre.phoenix.motorcontrol.NeutralMode

import frc.robot.Ports

object BallSystem : SubsystemBase() {

    private val lowerConveyorMotor = WPI_TalonSRX(Ports.BallSystem.LowerConveyor).apply() {
        setNeutralMode(NeutralMode.Brake)
    }

    private val verticalConveyorMotor = WPI_TalonSRX(Ports.BallSystem.VerticalConveyor).apply {
        setNeutralMode(NeutralMode.Brake)
    }

    private val shootSensor = DigitalInput(Ports.BallSystem.ShooterSensor)
    private val lowerConveyorSensor = DigitalInput(Ports.BallSystem.LowerConveyorSensor)

    private val shooting = false

    // Add forwards or runs below here.

    fun runLowerConveryor() {
        lowerConveyorMotor.set(ControlMode.PercentOutput, 1.0)
    }

    fun runLowerConveyorSlow() {
        lowerConveyorMotor.set(ControlMode.PercentOutput, 0.4)
    }

    fun runVerticalConveyor() {
        verticalConveyorMotor.set(ControlMode.PercentOutput, 1.0)
    }

    fun runVerticalConveyorSlow() {
        verticalConveyorMotor.set(ControlMode.PercentOutput, 0.4)
    }

    fun runAll() {
        runLowerConveryor()
        runVerticalConveyor()
    }

    fun runAllSlow() {
        runLowerConveyorSlow()
        runVerticalConveyorSlow()
    }

    // Add reverses below here

    fun reverseLowerConveyor() {
        lowerConveyorMotor.set(ControlMode.PercentOutput, -1.0)
    }

    fun reverseLowerConveyorSlow() {
        lowerConveyorMotor.set(ControlMode.PercentOutput, -0.4)
    }

    fun reverseVerticalConveyor() {
        verticalConveyorMotor.set(ControlMode.PercentOutput, -1.0)
    }

    fun reverseVerticalConveyorSlow() {
        verticalConveyorMotor.set(ControlMode.PercentOutput, -0.4)
    }

    fun reverseAll() {
        reverseLowerConveyor()
        reverseVerticalConveyor()
    }

    fun reverseAllSlow() {
        reverseLowerConveyorSlow()
        reverseVerticalConveyorSlow()
    }

    // Add stops below here.

    fun stopLowerConveyor() {
        lowerConveyorMotor.stopMotor()
    }

    fun stopVerticalConveyor() {
        verticalConveyorMotor.stopMotor()
    }

    fun stopAll() {
        stopLowerConveyor()
        stopVerticalConveyor()
    }

    fun isLowPrimed(): Boolean {
        return !lowerConveyorSensor.get() // '!' flips it, doing the equivalent of a Python 'not'
    }

    fun isUpperPrimed(): Boolean {
        return !shootSensor.get()
    }

    fun areTwoPrimed(): Boolean {
        return (!shootSensor.get() and !lowerConveyorSensor.get())
    }

    fun areTwoEmpty(): Boolean {
        return (shootSensor.get() and lowerConveyorSensor.get())
    }

}
