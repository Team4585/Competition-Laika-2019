package org.huskyrobotics.frc2019.subsystems.superstructure;

//import org.huskyrobotics.frc2019.subsystems.*;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.*;

import org.huskyrobotics.frc2019.inputs.Encoder.EncoderMode;

//import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PivotArm extends Subsystem {
      public void initDefaultCommand() 
	{
        //setDefaultCommand(new UseDrivetrain());
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
      }
      
      
      private double m_targetAngle;
      private double m_currentAngle;
      private double m_startAngle = 90;

      private TalonSRX m_motor;
      //private AnalogPotentiometer m_potent;
      //variables used for PID
      private final double kP = 1;//Speed Constant
      private final double kI = 0.001;//Speed up constant
      private final double kD = 1;//Slow down constant
      private final int kTimeoutMs = 100;
      private final int kF = 1;

      private boolean m_controlActive = true;

      /**
       * Calls the robot pivot arm
       * @param motorPort Talon SRX arm motor port
       * @param mode Type of encoder used
       */
      public PivotArm(int motorPort, EncoderMode mode) {
            m_motor = new TalonSRX(motorPort);

            if(mode == EncoderMode.QuadEncoder){
            m_motor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 100);
            }
            m_motor.setSensorPhase(true);
            m_motor.configNominalOutputForward(0, kTimeoutMs);
            m_motor.configNominalOutputReverse(0, kTimeoutMs);
            m_motor.configPeakOutputForward(1, kTimeoutMs);
            m_motor.configPeakOutputReverse(-1, kTimeoutMs);
            
            m_motor.selectProfileSlot(0, 0);
            m_motor.config_kF(0, kF, kTimeoutMs);
            m_motor.config_kP(0, kP, kTimeoutMs);
            m_motor.config_kI(0, kI, kTimeoutMs);
            m_motor.config_kD(0, kD, kTimeoutMs);
            m_motor.config_IntegralZone(0, 100, kTimeoutMs);

            m_startAngle = 90;
            m_currentAngle = m_startAngle;
            m_targetAngle = m_currentAngle;
            SmartDashboard.putNumber("Current Arm Angle", m_currentAngle);
            SmartDashboard.putNumber("Target Arm Angle", m_targetAngle);
      }
      public void setArmAxis(double input) {
            if(input > 0.1) {
                  goUp();
            } else if(input < -0.1) {
                  goDown();
            } else {
                  stop();
            }
      }
	public void setActive (boolean input) {
		m_controlActive = input;
	}
      //To be called by Robot.java. Will move the arm towards the target position.
      public void periodic() {
            calculateAngle();
            if(!m_controlActive) {
                  setTarget(m_startAngle);
            }
            m_motor.set(ControlMode.Position, m_targetAngle);
      }

      public double getCurrentAngle() {
            return m_startAngle - m_currentAngle;
      }

      public void setTarget(double angle) {
            m_targetAngle = angle;
      }

      public void goUp() {
            setTarget(m_startAngle+30);
      }
      public void goDown() {
            setTarget(m_startAngle-90);
      }
      public void stop() {
            setTarget(m_currentAngle);
      }
      //Used to calculate the current angle of the arm
      private void calculateAngle() {
            m_currentAngle = m_motor.getSelectedSensorPosition()/360;
      }
}
 
