/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.huskyrobotics.frc2019.inputs;
import org.ghrobotics.lib.mathematics.units.Length;
import org.ghrobotics.lib.mathematics.units.LengthKt;

public class VisionTargetFactory {

	private static final Length kHatchTapeHeight = LengthKt.getInch(12); //distance from floor to bottom of tape TODO I don't think we need this

	private static final Length kRocketCargoTapeHeight = LengthKt.getInch(20); //distance from floor to bottom of tape TODO I don't think we need this

	public static VisionTarget getRocketCargoDualTarget() {
		return new VisionTarget(VisionTarget.kDualVisionTapeShape, kRocketCargoTapeHeight);
	}

	public static VisionTarget getHatchDualTarget() {
		return new VisionTarget(VisionTarget.kDualVisionTapeShape, kHatchTapeHeight);
	}

	public static VisionTarget getRocketCargoSingleTarget() {
		return new VisionTarget(VisionTarget.kSingleVisionTapeShape, kRocketCargoTapeHeight);
	}

	public static VisionTarget getHatchSingleTarget() {
		return new VisionTarget(VisionTarget.kSingleVisionTapeShape, kHatchTapeHeight);
	}

}