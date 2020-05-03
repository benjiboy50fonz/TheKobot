package frc.team2539.robot.commands.intake

import frc.team2539.robot.cougartools.CougarCommand

import frc.team2539.robot.subsystems.Intake

import edu.wpi.first.wpilibj.Timer

class FumbleCommand(delay: Double = 2.0) : CougarCommand() {

    private val t = Timer()
    private var lastTime = 0.0

    private val d = delay

    init {
        addRequirements(Intake)
    }

    override fun initialize() {
        lastTime = 0.0
        t.start()
        Intake.fumble()
    }

    override fun execute() {
        var time = t.get()
        if (time - lastTime >= d) {
            Intake.fumble()
            lastTime = time
        }
    }

    override fun end(interrupted: Boolean) {
        Intake.stopIntake()
        t.stop()
        t.reset()
    }

}