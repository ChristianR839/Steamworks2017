package org.usfirst.frc.team839.robot.subsystems;

import org.usfirst.frc.team839.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The climber subsystem for the robot.
 * @author Matt
 */
public class Climber extends Subsystem
{

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
    
    public void climb(double power)
    {
    	RobotMap.climber.set(power);
    }
   
    public void stop()
    {
    	RobotMap.climber.set(0);
    }
}