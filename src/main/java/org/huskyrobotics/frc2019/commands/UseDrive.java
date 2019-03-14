/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.huskyrobotics.frc2019.commands;


import edu.wpi.first.wpilibj.command.Command;

import org.huskyrobotics.frc2019.DriveSignal;
import org.huskyrobotics.frc2019.Robot;

public class UseDrive extends Command {
  public UseDrive() {
    requires(Robot.m_Drive);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  DriveSignal m_Signal;
  Boolean isQuickTurn = (Robot.m_Oi.getRobotForward() < 0.1);

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.m_Drive.curvatureDrive(0, 0, false);
    Robot.m_Drive.setClosedLoop(DriveSignal.BRAKE);

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //SmartDashboard.putNumber("Gyro", m_Drive.getGyro(true));

    Robot.m_Drive.curvatureDrive(Robot.m_Oi.getRobotForward(), -Robot.m_Oi.getRobotTwist(), isQuickTurn);
    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_Drive.curvatureDrive(0, 0, false);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.m_Drive.curvatureDrive(0, 0, isQuickTurn);
  }
}
