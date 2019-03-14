/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.huskyrobotics.frc2019.subsystems.hatch;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.huskyrobotics.frc2019.Robot;
import org.huskyrobotics.frc2019.commands.UseHatch;
import org.huskyrobotics.frc2019.Util;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Hatch extends Subsystem{
    Joystick weapons = new Joystick(1);    
    TalonSRX m_motor;
    
    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new UseDrive());
        setDefaultCommand(new UseHatch(weapons.getY()));
    }

   
    public Hatch(int motorChannel) {
        m_motor = new TalonSRX(motorChannel);
        m_motor.configPeakCurrentLimit(20, 10);
        m_motor.setNeutralMode(NeutralMode.Brake);
        m_motor.configPeakOutputForward(+0.75, 10);
        m_motor.configPeakOutputReverse(-0.75, 10);
    }
    public void setMotor(double percent) {
        percent = Util.deadband(percent, 0.1);
        m_motor.set(ControlMode.PercentOutput, percent);
    }
}
