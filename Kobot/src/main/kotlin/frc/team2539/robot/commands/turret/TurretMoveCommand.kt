package frc.team2539.robot.commands.turret

import frc.team2539.robot.cougartools.CougarCommand

import frc.team2539.robot.subsystems.Turret
import frc.team2539.robot.Controls

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