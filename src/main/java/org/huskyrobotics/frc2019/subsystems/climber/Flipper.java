package org.huskyrobotics.frc2019.subsystems.climber;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Servo;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

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
    private Servo m_servoLeft;
    private Servo m_servoRight;


    private boolean m_solActive;

    private boolean m_controlActive = false;

    private static Flipper m_instance;
    public synchronized static Flipper getInstance() {
      if (m_instance == null) m_instance = new Flipper(RobotMap.kWinchMaster, RobotMap.kLeftSolenoid, RobotMap.kRightSolenoid, RobotMap.kLeftLinearActuator, RobotMap.kRightLinearActuator);
      return m_instance;
    }
    public Flipper (int winchMotorPort, int solenoidChannelLeft, int solenoidChannelRight, int servoChannelLeft, int servoChannelRight) {
        m_winchMotor = new TalonSRX(winchMotorPort);
        m_solLeft = new Solenoid(solenoidChannelLeft);
        m_solRight = new Solenoid(solenoidChannelRight);
        m_servoLeft = new Servo(solenoidChannelLeft);
        m_servoRight = new Servo(solenoidChannelRight);
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
    public void periodic () {
        m_solLeft.set(m_solActive);
        m_solRight.set(m_solActive);
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
        System.out.println("Armstrong is locking!!!");
        m_servoLeft.set(1);
        m_servoRight.set(1);
    }
    public void unlock() {
        System.out.println("Armstrong is unlocking!!!");
        m_servoLeft.set(0);
        m_servoRight.set(0);
    }
}
