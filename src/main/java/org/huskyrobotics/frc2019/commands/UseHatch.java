/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.huskyrobotics.frc2019.commands;

import org.huskyrobotics.frc2019.OI;
import org.huskyrobotics.frc2019.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;

public class UseHatch extends TimedCommand {

  public UseHatch(double timeout, OI oi) {
    super(timeout);
    requires(Robot.m_Kennedy);
    // Use requires() here to declare subsystem dependencies
    // eg. requirdoublees(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.m_Kennedy.setMotor(0);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double motorValue = 0.25;// Change motor value to something
    while(Robot.m_Oi.contractHatchButton()){
      Robot.m_Kennedy.setMotor(motorValue); 
    }
    while(Robot.m_Oi.expandHatchButton()){
      Robot.m_Kennedy.setMotor(-motorValue);
    }
    Robot.m_Kennedy.setMotor(Robot.m_Oi.getHatchAxis());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_Kennedy.setMotor(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.m_Kennedy.setMotor(0);
  }
}
