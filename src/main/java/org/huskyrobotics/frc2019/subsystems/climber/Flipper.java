package org.huskyrobotics.frc2019.subsystems.climber;

import edu.wpi.first.wpilibj.Solenoid;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
//import edu.wpi.first.wpilibj.LinearServo;

public class Flipper {
    private TalonSRX m_winchMotor;
    private Solenoid m_solLeft;
    private Solenoid m_solRight;

    private boolean m_solActive;

    private boolean m_controlActive = false;
    private boolean m_locked = false;

    public Flipper (int winchMotorPort, int solenoidChannelLeft, int solenoidChannelRight) {
        m_winchMotor = new TalonSRX(winchMotorPort);
        m_solLeft = new Solenoid(solenoidChannelLeft);
        m_solRight = new Solenoid(solenoidChannelRight);
    }
    //releases the winch rope
    public void setWinchAxis(double input) {
        if(m_controlActive) {
            if(Math.abs(input) > 0.1) {
                m_winchMotor.set(ControlMode.PercentOutput, input);
            } else {
                m_winchMotor.set(ControlMode.PercentOutput, 0);
            }
        }
    }
	public void setActive (boolean input) {
		m_controlActive = !input;
	}
    public void periodic() {
        if(m_controlActive) {
            clamp(true);
        }
        m_solLeft.set(m_solActive);
        m_solRight.set(m_solActive);
        if(m_locked) {

        }
    }

    //Clamps onto the platform so winch can pull robot up.
    //True for clamped, false for released/
    public void clamp(boolean clamp) {
        m_solActive = clamp;
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
        m_locked = true;
    }
    public void unlock() {
        m_locked = false;
    }
}
