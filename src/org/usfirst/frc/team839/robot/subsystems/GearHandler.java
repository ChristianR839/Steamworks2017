package org.usfirst.frc.team839.robot.subsystems;

import org.usfirst.frc.team839.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearHandler extends Subsystem {

	public void in(){
		RobotMap.gearHandlerL.set(DoubleSolenoid.Value.kReverse);		
	}
	public void out(){
		RobotMap.gearHandlerL.set(DoubleSolenoid.Value.kForward);
	}
	public void stop(){
		RobotMap.gearHandlerL.set(DoubleSolenoid.Value.kOff);
	}
    public void initDefaultCommand() {
     }
}

