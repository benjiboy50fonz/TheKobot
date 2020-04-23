package frc.robot.commands.shooter

import frc.robot.cougartools.CougarCommand

import frc.robot.subsystems.Limelight
import frc.robot.subsystems.LEDs
import frc.robot.subsystems.Shooter

import edu.wpi.first.wpilibj.Timer

class ShooterLimelightCommand : CougarCommand() {

    private val close = false
    private val timer = Timer()
    private var secondCounter = 0.0

    init {
        addRequirements(Shooter)
    }

    override fun initialize() {
        Limelight.setPipeline(1)
        LEDs.solidRed()

        timer.start()
        secondCounter = 0.0
    }

    override fun execute() {
        var speed = 4800 - 850 * Limelight.getArea()

        if (speed > 4800.0) { speed = 4800.0 }
        if (timer.hasElapsed(secondCounter)) {
            secondCounter += 2 // Add more nt stuff here. Look at GitHub.
        }
    }

    override fun end(interrupted: Boolean) {
        Shooter.stopShooter()
        Limelight.setPipeline(0)
        LEDs.turnOff()

        timer.stop()
        timer.reset()
    }

}