package frc.robot.subsystems

import edu.wpi.first.wpilibj2.command.SubsystemBase

import 

import frc.robot.Ports

object Intake : SubsystemBase() {
    private val intakeMotor = CANSparkMAX(Ports.Intake.IntakeMotor, ).apply

}