/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.huskyrobotics.frc2019.commands;

import org.ghrobotics.lib.mathematics.units.Rotation2d;
import org.huskyrobotics.frc2019.Robot;
import org.huskyrobotics.frc2019.inputs.*;
import org.huskyrobotics.frc2019.Constants;

import edu.wpi.first.wpilibj.command.Command;

public class Turn extends Command {

	double starting_angle;
	double target_angle_relative;
	double target_angle;
	double target_angle_absolute;
	boolean isAbsolute = false;
	double output;
	double max_turn_speed;
	double raw_left;
	double raw_right;

	// TerriblePID turnPID = new TerriblePID(RobotConfig.auto.TurnInPlace.kp, RobotConfig.auto.TurnInPlace.ki, 
	//   RobotConfig.auto.TurnInPlace.min_turn_speed, 
	//   RobotConfig.auto.TurnInPlace.max_turn_speed, 
	//   RobotConfig.auto.TurnInPlace.integral_zone, 
	//   RobotConfig.auto.TurnInPlace.max_integral);

	double kp = 0.015 * 2f;
	double ki = 0.0;
	double kd = 0.1 * 2f;
	double integralZone = 1000;
	double maxIntegralAccum = 1000;

	// public TerriblePID(double kp, double ki, double minOutput, double maxOutput, double integralZone, double maxIntegralAccum, double kf, FeedForwardMode feedforwardmode, FeedForwardBehavior feedforwardbehavior, double unitsPerQuarterWave) {
	// TerriblePID turnPID = new TerriblePID(kp,
	//   RobotConfig.auto.turnInPlace.max_turn_speed
	// );

	TestPID turnPID = new TestPID(kp, ki, kd, 0, -1, 1, integralZone, maxIntegralAccum, 0, null, null);

	/**
	 * Turn a specified number of degrees in the default auto gear.
	 * This constructor will default to taking the angle relative to
	 * the robot's angle when the command is initialized, not the 
	 * absolute angle. If you want to specify, use a bool as the second
	 * argument to specify if the angle should be interpreted as absolute 
	 * or not.
	 * @param target_angle
	 */
	public Turn(double target_angle) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.m_Drive);
		this.target_angle = target_angle;
	}

	/**
	 * Turn a specified number of degrees in the default auto gear. 
	 * The angle passed is an absolute angle relative to the 
	 * angle upon autonomous init.
	 * @param target_angle
	 * @param isAbsolute
	 */
	public Turn(Rotation2d target_angle, boolean isAbsolute) {
		this.isAbsolute = isAbsolute;
		// Use requires() here to declare subsystem dependencies
		requires(Robot.m_Drive);
		this.target_angle = target_angle.getDegree();
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		starting_angle = Robot.m_Drive.getGyro();

		// If the angle is relative (which it should not be), setup target angle.
		// Otherwise the angle is absolute (relative to auto init) so we don't care.
		if (!(isAbsolute)) { // if isAbsolute is false, and we want a relative angle
			target_angle = target_angle + starting_angle;
		}

		turnPID.setSetpoint(target_angle);
		System.out.println("Turn in place init'ed!");

	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		output = turnPID.update(Robot.m_Drive.getGyro() % 360);
		raw_left = Encoder.distanceToRaw(output, Constants.drivetrain.left_wheel_effective_diameter, Constants.drivetrain.kPPR);
		raw_right = (-1) * Encoder.distanceToRaw(output, Constants.drivetrain.right_wheel_effective_diameter, Constants.drivetrain.kPPR);
		Robot.m_Drive.setPowers(output, -output);
		System.out.println(String.format("Turn in place execute! Target: %s Gyro output: %s,Output: %s, Raw left: %s Raw right %s", turnPID.getSetpoint(), Robot.m_Drive.getGyro(), output, raw_left, raw_right));
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return (turnPID.getError() < 10);
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {}
}