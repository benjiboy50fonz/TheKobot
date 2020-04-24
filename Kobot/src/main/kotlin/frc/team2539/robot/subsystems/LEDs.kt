package frc.team2539.robot.subsystems

import edu.wpi.first.wpilibj2.command.SubsystemBase
import edu.wpi.first.wpilibj.Spark

import frc.team2539.robot.Ports
import frc.team2539.robot.cougartools.CougarSubsystem

object LEDs : CougarSubsystem() {

    private val controller = Spark(Ports.LEDSystem.Controller)

    fun turnOff() {
        controller.set(0.99)
    }

    fun solidRed() {
        controller.set(0.61)
    }

    fun solidOrange() { // Or what they say is orange lol.
        controller.set(0.65)
    }

    fun solidGreen() {
        controller.set(0.77)
    }

    fun solidBlue() {
        controller.set(0.87)
    }

    fun solidWhite() {
        controller.set(0.93)
    }

    fun flashRed() {
        controller.set(-0.11)
    }

    fun flashWhite() {
        controller.set(-0.05)
    }

    fun rainbowLava() {
        controller.set(-0.93)
    }

    fun setCustom(color: Double) {
        controller.set(color)
    }

}
