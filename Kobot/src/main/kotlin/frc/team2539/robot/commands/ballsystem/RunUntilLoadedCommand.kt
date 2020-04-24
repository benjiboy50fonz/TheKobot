package frc.team2539.robot.commands.ballsystem

import frc.team2539.robot.cougartools.CougarCommand

import frc.team2539.robot.subsystems.BallSystem
import frc.team2539.robot.subsystems.Intake

class RunUntilLoadedCommand : CougarCommand() {

    init {
        addRequirements(BallSystem)
        addRequirements(Intake)
    }

    override fun initialize() {
        Intake.intake()

        if (!BallSystem.areTwoPrimed()) {
            if (!BallSystem.isLowPrimed() and BallSystem.isUpperPrimed()) {
                BallSystem.runLowerConveyorSlow()
            }
            else {
                BallSystem.runAllSlow()
            }
        }
    }

    override fun execute() {
        if (BallSystem.areTwoPrimed()) {
            BallSystem.stopLowerConveyor()
        }

        else if (BallSystem.isUpperPrimed()) {
            BallSystem.stopVerticalConveyor()
        }
    }

    override fun end(interrupted: Boolean) {
        BallSystem.stopAll()
        Intake.stopIntake()
    }

}