package org.usfirst.frc.team839.robot.subsystems;

import org.usfirst.frc.team839.robot.RobotMap;

public class LightRing 
{
	public void on()
	{
		RobotMap.lightRing.set(true);
	}
	public void toggle()
	{
		RobotMap.lightRing.set(!RobotMap.lightRing.get());
	}
	
	public void off()
	{
		RobotMap.lightRing.set(false);
	}
}
