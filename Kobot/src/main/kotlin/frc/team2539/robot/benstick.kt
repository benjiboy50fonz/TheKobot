package frc.team2539.robot

import edu.wpi.first.wpilibj.Joystick

import edu.wpi.first.wpilibj2.command.button.JoystickButton
import edu.wpi.first.wpilibj2.command.button.POVButton

abstract class Benstick(id: Int) : Joystick(id) {

    open fun createButton(button: Int): edu.wpi.first.wpilibj2.command.button.Button {
        return if (button >= 20) {
            POVButton(this, button)
        } else {
            JoystickButton(this, button)
        }
    }

}