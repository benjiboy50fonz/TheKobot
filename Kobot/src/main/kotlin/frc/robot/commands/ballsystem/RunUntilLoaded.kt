package frc.robot.commands.ballsystem

import edu.wpi.first.wpilibj2.command.CommandBase

import frc.robot.subsystems.BallSystem
import frc.robot.subsystems.Intake

class RunUntilLoaded : CommandBase() {

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