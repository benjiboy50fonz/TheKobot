package frc.team2539.robot.commands.ballsystem

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup

import frc.team2539.robot.commands.intake.RunIntakeCommand

import frc.team2539.robot.subsystems.Intake
import frc.team2539.robot.subsystems.BallSystem

class RunBallFlowCommandGroup : ParallelCommandGroup() { // This is rather wacky...

    init {

        addRequirements(Intake, BallSystem)

        this.addCommands(
                RunAllCommand(),
                RunIntakeCommand()
        )

    }

}