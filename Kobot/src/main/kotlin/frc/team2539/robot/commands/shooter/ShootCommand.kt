package frc.team2539.robot.commands.shooter

import frc.team2539.robot.cougartools.CougarCommand

import frc.team2539.robot.subsystems.Shooter
import frc.team2539.robot.subsystems.LEDs

class ShootCommand(rpm: Double) : CougarCommand() {

    private var rpm = rpm

    init {
        addRequirements(Shooter)
        addRequirements(LEDs)
    }

    override fun initialize() {
        Shooter.setRPM(rpm)
        LEDs.flashRed()
    }

    override fun execute() {
        var currentRPM = Shooter.getRPM()

        if (currentRPM >= rpm - 200) { LEDs.solidRed() }
        else { LEDs.flashRed() }

    }

    override fun end(interrupted: Boolean) {
        Shooter.stopShooter()
        LEDs.turnOff()
    }

}