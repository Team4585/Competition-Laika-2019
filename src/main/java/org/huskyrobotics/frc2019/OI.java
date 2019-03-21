package org.huskyrobotics.frc2019;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import java.util.HashMap;

import org.huskyrobotics.frc2019.commands.ShiftHigh;
import org.huskyrobotics.frc2019.commands.ShiftLow;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class OI {
  private Joystick m_HelmStick;
  private Joystick m_WeaponStick;

  private HashMap[] helmControls;
  private HashMap<String, Integer> controlsHar;//holds info for Harold's driver mapping
  private HashMap<String, Integer> controlsMan;//holds info for Manvith's driver mapping

  private HashMap[] weaponsControls;
  private HashMap<String, Integer> controlsMic;//holds info for Micaela's driver mapping
  private HashMap<String, Integer> controlsAhn;//holds info for Ahnaff's driver mapping

  SendableChooser<Integer> m_helm = new SendableChooser<>();
  SendableChooser<Integer> m_weapon = new SendableChooser<>();
  
  private JoystickButton shiftHigh;
  private JoystickButton shiftLow;
  
  public OI (int HELMSTICKPORT, int WEAPONSTICKPORT) {
    m_HelmStick = new Joystick(HELMSTICKPORT);
    m_WeaponStick = new Joystick(WEAPONSTICKPORT);

    m_helm.addOption("Harold (Joystick)", 0);
    m_helm.addOption("Manvith (Controller)", 1);

    m_weapon.addOption("Micaela (Joystick)", 0);
    m_weapon.addOption("Ahnaff (Controller)", 1);

    shiftHigh = new JoystickButton(m_HelmStick, 8);
    shiftLow = new JoystickButton(m_HelmStick, 7);
    shiftHigh.whenPressed(new ShiftHigh());
    shiftLow.whenPressed(new ShiftLow());



    SmartDashboard.putData("Helm Driver", m_helm);
    SmartDashboard.putData("Weapons Officer", m_weapon);

    controlsHar = new HashMap<String, Integer>();
    controlsHar.put("RobotForward", 1);
    controlsHar.put("RobotTwist", 2);
    controlsHar.put("TeleopSwitch", 6);
    controlsHar.put("ShiftUp", 8);
    controlsHar.put("ShiftDown", 7);

    controlsMan = new HashMap<String, Integer>();
    controlsMan.put("RobotForward", 1);
    controlsMan.put("RobotTwist", 0);
    controlsMan.put("TeleopSwitch", 8);

    helmControls = new HashMap[] {controlsHar, controlsMan};

    controlsMic = new HashMap<String, Integer>();
    controlsMic.put("ArmAxis", 5);
    controlsMic.put("WinchAxis", 0);
    controlsMic.put("CargoAxis", 3);
    controlsMic.put("WinchIn", 4);
    controlsMic.put("WinchOut", 2);
    controlsMic.put("CargoIn", -1);
    controlsMic.put("CargoOut", -1);
    controlsMic.put("HatchAxis", -1);
    controlsMic.put("HatchIn", -6);
    controlsMic.put("HatchOut", -4);
    controlsMic.put("ToggleClimb", 3);
    controlsMic.put("UnlockClimber", 11);


    controlsAhn = new HashMap<String, Integer>();
    controlsAhn.put("ArmAxis", 2);
    controlsAhn.put("WinchAxis", 2);
    controlsAhn.put("CargoAxis", 0);
    controlsAhn.put("WinchIn", -1);
    controlsAhn.put("WinchOut", -1);
    controlsAhn.put("CargoIn", -3);
    controlsAhn.put("CargoOut", -4);
    controlsAhn.put("HatchAxis", -1);
    controlsAhn.put("HatchIn", 3);
    controlsAhn.put("HatchOut", 4);
    controlsAhn.put("ToggleClimb", 1);
    controlsAhn.put("UnlockClimber", 2);

    weaponsControls = new HashMap[] {controlsMic, controlsAhn};
  }
  private Integer getCurrentHelm() {
    if (m_helm.getSelected() != null && m_helm.getSelected() >= 0 && m_helm.getSelected() < helmControls.length) {
      return(m_helm.getSelected());
    } else {
      return(0);
    }
  }
  private Integer getCurrentWeapons() {
    if (m_weapon.getSelected() != null && m_weapon.getSelected() >= 0 && m_weapon.getSelected() < weaponsControls.length) {
      return(m_weapon.getSelected());
    } else {
      return(0);
    }
  }
  //These functions will be used by robot.java to get the input
  public double getRobotForward () {//The value used for robot motors moving forward. Should be put into Drive
    return m_HelmStick.getRawAxis((int) helmControls[getCurrentHelm()].get("RobotForward"));
  }
  public double getRobotTwist () {//The value used for robot motors twisting. Should be put into Drive
    return m_HelmStick.getRawAxis((int) helmControls[getCurrentHelm()].get("RobotTwist"));
  }
  public boolean getTeleopSwitch () {//The value used to switch to teleop incase auto fails. Should be put somewhere? ¯\_(ツ)_/¯
    return m_HelmStick.getRawButton((int) helmControls[getCurrentHelm()].get("TeleopSwitch"));
  }

  public double getArmAxis () {//The value used for moving the arm up and down. Should be put into PivotArm
    return m_WeaponStick.getRawAxis((int) weaponsControls[getCurrentWeapons()].get("ArmAxis"));

  }
  public double getCargoAxis () {//The value used for controlling the cargo intake. Should be put into CargoIO
    if ((int) weaponsControls[m_weapon.getSelected()].get("CargoIn") >= 0) {
      if (m_WeaponStick.getRawButton((int) weaponsControls[getCurrentWeapons()].get("CargoIn"))) {
        return (0.5);
      } else if (m_WeaponStick.getRawButton((int) weaponsControls[getCurrentWeapons()].get("CargoOut"))) {
        return (-0.5);
      } else {
        return (0);
      }
    }
    if ((int) weaponsControls[m_weapon.getSelected()].get("CargoAxis") >= 0) {
      return (m_WeaponStick.getRawAxis((int) weaponsControls[getCurrentWeapons()].get("CargoAxis")));
    }
    return(0);
  }
  public double getWinchAxis () {
    if ((int) weaponsControls[m_weapon.getSelected()].get("WinchIn") >= 0) {
      if (m_WeaponStick.getRawButton((int) weaponsControls[getCurrentWeapons()].get("WinchIn"))) {
        return (0.5);
      } else if (m_WeaponStick.getRawButton((int) weaponsControls[getCurrentWeapons()].get("WinchOut"))) {
        return (-0.5);
      } else {
        return (0);
      }
    }
    if ((int) weaponsControls[m_weapon.getSelected()].get("WinchAxis") >= 0) {
      return (m_WeaponStick.getRawAxis((int) weaponsControls[getCurrentWeapons()].get("WinchAxis")));
    }
    return(0);
  }
  public double getHatchAxis() {
    if ((int) weaponsControls[m_weapon.getSelected()].get("HatchIn") >= 0) {
      if (m_WeaponStick.getRawButton((int) weaponsControls[getCurrentWeapons()].get("HatchIn"))) {
        return (0.2);
      } else if (m_WeaponStick.getRawButton((int) weaponsControls[getCurrentWeapons()].get("HatchOut"))) {
        return (-0.2);
      } else {
        return (0);
      }
    }
    if ((int) weaponsControls[m_weapon.getSelected()].get("HatchAxis") >= 0) {
      return (m_WeaponStick.getRawAxis((int) weaponsControls[getCurrentWeapons()].get("HatchAxis")));
    }
    return(0);
  }
  public boolean contractHatchButton() {
    return(m_WeaponStick.getRawButton((int) weaponsControls[getCurrentWeapons()].get("HatchIn")));
  }
  public boolean expandHatchButton() {
    return(m_WeaponStick.getRawButton((int) weaponsControls[getCurrentWeapons()].get("HatchOut")));
  }

  public boolean getClimbActive () {
    return m_WeaponStick.getRawButtonPressed((int) weaponsControls[getCurrentWeapons()].get("ToggleClimb"));
  }
  public boolean getArmstrongLocked () {
    return m_WeaponStick.getRawButtonPressed((int) weaponsControls[getCurrentWeapons()].get("UnlockClimber"));
  }
  // public boolean getRotate(){
  //   return m_WeaponStick.getRawButton(controlsW.get("ArmRotate"));
  // }
}

