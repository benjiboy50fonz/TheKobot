package frc.team2539.robot.commands.limelight

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup

import frc.team2539.robot.subsystems.Hood
import frc.team2539.robot.subsystems.Turret
import frc.team2539.robot.subsystems.Shooter

import frc.team2539.robot.commands.hood.HoodLimelightCommand
import frc.team2539.robot.commands.turret.TurretLimelightCommand
import frc.team2539.robot.commands.shooter.ShooterLimelightCommand

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup

class SudoCommandGroup :  SequentialCommandGroup() {

    init {
        addRequirements(Hood, Turret, Shooter)

        addCommands(
                ParallelCommandGroup(
                        HoodLimelightCommand(),
                        TurretLimelightCommand()
                ),
                ShooterLimelightCommand()
        )
    }

}