package org.usfirst.frc.team839.robot.subsystems;

import org.usfirst.frc.team839.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The shooter subsystem for the robot.
 * @author Matt
 */
public class Intake extends Subsystem
{

    public void initDefaultCommand() 
    {
    	
    }
    
    public void intake()
    {
    	RobotMap.intake.set(1);
    }
   
    public void stop()
    {
    	RobotMap.intake.set(0);
    }

}