package org.huskyrobotics.frc2019;

import org.ghrobotics.lib.mathematics.units.Length;
import org.ghrobotics.lib.mathematics.units.LengthKt;
import org.ghrobotics.lib.mathematics.units.Mass;
import org.ghrobotics.lib.mathematics.units.MassKt;
import org.ghrobotics.lib.mathematics.units.Time;
import org.ghrobotics.lib.mathematics.units.TimeUnitsKt;

import com.team254.lib.physics.DCMotorTransmission;
import com.team254.lib.physics.DifferentialDrive;
import org.huskyrobotics.frc2019.Util;
public class ConstantsAuto {
	/* Graciously borrowed from 5190*/
	public static final double kRobotMass = 40 /* Robot, kg */ + 5f /* Battery, kg */ + 2f /* Bumpers, kg */;
	public static final double kRobotMomentOfInertia = 10.0; // kg m^2 // TODO Tune
	public static final double kRobotAngularDrag = 12.0; // N*m / (rad/sec)

	public static final double kWheelRadius = Util.toMeters(2f / 12f);// meters. TODO tune
	public static final double kTrackWidth = Util.toMeters(23.5f / 12f);// meters

	private static final double kVDriveLeftLow = 0.275 * 1d; // Volts per radians per second - Calculated emperically
	private static final double kADriveLeftLow = 0.027 * 1d; // Volts per radians per second per second TODO tune
	private static final double kVInterceptLeftLow = 0.95 * 1d; // Volts - tuned!

	private static final double kVDriveRightLow = 0.275 * 1d; // Volts per radians per second - Calculated emperically
	private static final double kADriveRightLow = 0.027 * 1d; // Volts per radians per second per second TODO tune
	private static final double kVInterceptRightLow = 0.96 * 1d; // Volts - tuned!

	public static final DCMotorTransmission kLeftTransmissionModelLowGear = new DCMotorTransmission(1 / kVDriveLeftLow,
			kWheelRadius * kWheelRadius * kRobotMass / (2.0 * kADriveLeftLow),
			kVInterceptLeftLow);

	public static final DCMotorTransmission kRightTransmissionModelLowGear = new DCMotorTransmission(1 / kVDriveRightLow,
			kWheelRadius * kWheelRadius * kRobotMass / (2.0 * kADriveRightLow),
			kVInterceptRightLow);

	private static final double kVDriveLeftHigh = 0.137 * 1d; // Volts per radians per second - Calculated emperically
	private static final double kADriveLeftHigh = 0.035 * 1d; // Volts per radians per second per second 
	private static final double kVInterceptLeftHigh = 1.23 * 1d;//4 * 0.4d; // Volts - tuned!

	private static final double kVDriveRightHigh = 0.135 * 1d; // Volts per radians per second - Calculated emperically
	private static final double kADriveRightHigh = 0.035 * 1d; // Volts per radians per second per second 
	private static final double kVInterceptRightHigh = 1.21 * 1d;//4 * 0.4d; // Volts - tuned!

	private static final DCMotorTransmission kLeftTransmissionModelHighGear = new DCMotorTransmission(1 / kVDriveLeftHigh,
			kWheelRadius * kWheelRadius * kRobotMass / (2.0 * kADriveLeftHigh),
			kVInterceptLeftHigh);

	private static final DCMotorTransmission kRightTransmissionModelHighGear = new DCMotorTransmission(1 / kVDriveRightHigh,
			kWheelRadius * kWheelRadius * kRobotMass / (2.0 * kADriveRightHigh),
			kVInterceptRightHigh);

	public static final DifferentialDrive kLowGearDifferentialDrive = new DifferentialDrive(kRobotMass, kRobotMomentOfInertia,
			kRobotAngularDrag, kWheelRadius, kTrackWidth / 2.0, kLeftTransmissionModelLowGear, kRightTransmissionModelLowGear);

	public static final DifferentialDrive kHighGearDifferentialDrive = new DifferentialDrive(kRobotMass, kRobotMomentOfInertia,
      kRobotAngularDrag, kWheelRadius, kTrackWidth / 2.0, kLeftTransmissionModelHighGear, kRightTransmissionModelHighGear);
      
      
	/* Ramsete constants */
	public static final double kDriveBeta = 2 * 1d; // Inverse meters squared
	public static final double kDriveZeta = 0.7 * 1d; // Unitless dampening co-efficient

	/* Pure Pursuit constants */
	public static final double kLat = 0.05f;
	public static final Time kLookaheadTime = TimeUnitsKt.getSecond(0.1);
	public static final Length kMinLookaheadDistance = LengthKt.getFeet(2);


}