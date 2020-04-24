package frc.robot.commands.turret

import frc.robot.cougartools.CougarCommand

import frc.robot.subsystems.Turret
import frc.robot.subsystems.Limelight

import kotlin.math.abs
import kotlin.math.sign

class TurretLimelightCommand : CougarCommand() {

    private var count = 0

    init {
        addRequirements(Turret)
    }

    override fun initialize() {
        Limelight.setPipeline(1)
        count = 0
    }

    override fun execute() {
        var rotate = Limelight.getX() * -0.03

        rotate = if (abs(rotate) > 0.5) { sign(rotate) * 0.5 } else { rotate } // Takes the sign of rotate, and applies it to the 50% rotate cap.

        Turret.moveTurret(rotate)

        if (count >= 4) { Limelight.takeSnapshot() }
        else { count += 1 }

    }

    override fun end(interrupted: Boolean) {
        Turret.stopTurret()
        Limelight.setPipeline(0)
    }

}