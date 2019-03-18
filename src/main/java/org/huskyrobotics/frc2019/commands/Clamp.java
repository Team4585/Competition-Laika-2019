/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.huskyrobotics.frc2019.commands;

import org.huskyrobotics.frc2019.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 * Add your docs here.
 */
public class Clamp extends InstantCommand {
  /**
   * Add your docs here.
   */
  public Clamp() {
    super();
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);;
    requires(Robot.m_Armstrong);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    Robot.m_Armstrong.clamp(true);
  }

}
