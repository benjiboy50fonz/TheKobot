package frc.robot

import edu.wpi.first.wpilibj.Joystick
import edu.wpi.first.wpilibj.GenericHID.Hand

import edu.wpi.first.wpilibj2.command.button.JoystickButton
import edu.wpi.first.wpilibj2.command.button.POVButton

class LogitechDualShock(id_: Int) : Joystick(id_) {

    val A = createButton(2)
    val B = createButton(3)
    val X = createButton(1)
    val Y = createButton(4)

    val LeftBumper = createButton(5)
    val RightBumper = createButton(6)
    val LeftTrigger = createButton(7)
    val RightTrigger = createButton(8)

    val Back = createButton(9)
    val Start = createButton(10)

    val LeftJoystick = createButton(11)
    val RightJoystick = createButton(12)

    val DPadUp = createButton(20)
    val DPadDown = createButton(22)
    val DPadRight = createButton(21)
    val DPadLeft = createButton(23)

    fun getLeftX(): Double {
        return this.getX(Hand.kLeft)
    }

    fun getRightX(): Double {
        return this.getX(Hand.kRight)
    }

    fun getLeftY(): Double {
        return this.getY(Hand.kLeft)
    }

    fun getRightY(): Double {
        return this.getY(Hand.kRight)
    }

    fun createButton(button: Int): edu.wpi.first.wpilibj2.command.button.Button {
        return if (button >= 20) {
            POVButton(this, button)
        } else {
            JoystickButton(this, button)
        }
    }

}