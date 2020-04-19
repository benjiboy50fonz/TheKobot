package frc.robot

import edu.wpi.first.wpilibj.Joystick

import edu.wpi.first.wpilibj2.command.button.JoystickButton
import edu.wpi.first.wpilibj2.command.button.POVButton

class LogitechDualShock(id_: Int) : Joystick(id_){

    private val buttonNames = mapOf(
            "A" to 2,
            "B" to 3,
            "X" to 1,
            "Y" to 4,
            "LeftBumper" to 5,
            "RightBumper" to 6,
            "LeftTrigger" to 7,
            "RightTrigger" to 8,
            "Back" to 9,
            "Start" to 10,
            "LeftJoystick" to 11,
            "RightJoystick" to 12,
            "DPadUp" to 20,
            "DPadDown" to 22,
            "DPadRight" to 21,
            "DPadLeft" to 23
    )

    fun createButton(button: String?): edu.wpi.first.wpilibj2.command.button.Button {
        return if (this.buttonNames[button]!!.toInt() >= 20) {
            POVButton(this, this.buttonNames[button]!!.toInt())
        }
        else {
            JoystickButton(this, this.buttonNames[button]!!.toInt())
        }
    }
}