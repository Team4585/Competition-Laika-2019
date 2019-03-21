package org.huskyrobotics.frc2019.subsystems.climber;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
//import edu.wpi.first.wpilibj.LinearServo;

import org.huskyrobotics.frc2019.RobotMap;
import org.huskyrobotics.frc2019.commands.*;

public class Flipper extends Subsystem {
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new UseDrive());
        setDefaultCommand(new Armstrong());
    }
    private TalonSRX m_winchMotor;
    private Solenoid m_solLeft;
    private Solenoid m_solRight;

    private boolean m_solActive;

    private boolean m_controlActive = false;

    private static Flipper m_instance;
    public synchronized static Flipper getInstance() {
      if (m_instance == null) m_instance = new Flipper(RobotMap.kWinchMaster, 1, 2);
      return m_instance;
    }
    public Flipper (int winchMotorPort, int solenoidChannelLeft, int solenoidChannelRight) {
        m_winchMotor = new TalonSRX(winchMotorPort);
        m_solLeft = new Solenoid(solenoidChannelLeft);
        m_solRight = new Solenoid(solenoidChannelRight);
    }
    //releases the winch rope
    public void setWinchAxis(double input) {
        if(m_controlActive) {
            if(Math.abs(input) > 0.1) {
                m_winchMotor.set(ControlMode.Position, input);
            } else {
                m_winchMotor.set(ControlMode.PercentOutput, 0);
            }
        }
    }
	public void setActive (boolean input) {
		m_controlActive = !input;
	}

    //Clamps onto the platform so winch can pull robot up.
    //True for clamped, false for released/
    public void clamp(boolean clamp) {
        m_solActive = clamp;
        m_controlActive = clamp;
    }
    public void toggleClamp() {
        m_solActive = !m_solActive;
    }
    public boolean getClamped() {
        return(m_solActive);
    }
    //stops the winch
    public void stopWinch() {
        m_winchMotor.set(ControlMode.PercentOutput, 0.0);
    }

    public void lock() {
        m_solLeft.set(true);
        m_solRight.set(true);
    }
    public void unlock() {
        m_solLeft.set(false);
        m_solRight.set(false);
    }
}
