/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.huskyrobotics.frc2019;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;


  // TalonSRXs
  // public static final int kLeftMaster = 0;
  // public static final int kRightMaster = 1;
  // public static final int kPivotMaster = 2;
  // public static final int kWinchMaster = 3;


  public static final int kLeftMaster = 4;
  public static final int kRightMaster = 0;
  public static final int kPivotMaster = 2;
  public static final int kWinchMaster = 11;
  public static final int kHatchMaster = 22;


  // VictorSPXs
  // public static final int kLeftSlave = 0;
  // public static final int kRightSlave = 1;
  // public static final int kIntake = 4;
  // public static final int kTell_Me_What_We_Need = 4585;

  public static final int kLeftSlave = 2;
  public static final int kRightSlave = 1;
  public static final int kIntake = 4;
  public static final int kTell_Me_What_We_Need = 4585;


  //PigeonIMU
  //ORIGINAL PORT 0
  public static final int kCANPigeon = 0;

  // Compressor
  //ORIGINAL PORT 0
  public static final int COMPRESSOR = 5;

  //Linear Actuators
  public static final int kLeftLinearActuator = 0;
  public static final int kRightLinearActuator = 1;
  //Pneumatics
  public static final int kLeftSolenoid = 1;
  public static final int kRightSolenoid = 2;
}
