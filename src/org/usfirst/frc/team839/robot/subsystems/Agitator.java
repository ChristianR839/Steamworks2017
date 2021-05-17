package org.usfirst.frc.team839.robot.subsystems;

import org.usfirst.frc.team839.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Agitator extends Subsystem
{

    public void initDefaultCommand() 
    {
    	
    }
    
    public void agitate(double power)
    {
    	RobotMap.agitator.set(-power);
    }
   
    public void stop()
    {
    	RobotMap.agitator.set(0);
    }
}
