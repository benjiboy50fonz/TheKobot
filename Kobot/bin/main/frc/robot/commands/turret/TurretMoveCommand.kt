package frc.robot.commands.turret

import frc.robot.cougartools.CougarCommand

import frc.robot.subsystems.Turret
import frc.robot.Controls

class TurretMoveCommand : CougarCommand() {

    init {
        addRequirements(Turret)
    }

    override fun execute() {
        Turret.fieldMove(Controls.operatorController.getRightX() * -0.75)
    }

    override fun end(interrupted: Boolean) {
        Turret.stopTurret()
    }

}