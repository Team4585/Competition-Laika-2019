/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.huskyrobotics.frc2019.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;

import org.huskyrobotics.frc2019.Robot;
import org.huskyrobotics.frc2019.subsystems.drive.FalconDrive;

/**
 * Add your docs here.
 */
public class ShiftLow extends InstantCommand {
  /**
   * Add your docs here.
   */
  FalconDrive drive = FalconDrive.getInstance();
  Boolean m_isHigh;
  public ShiftLow() {
    super();
    requires(drive);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    Robot.m_Drive.setLowGear();
    m_isHigh = false;
  }

}
