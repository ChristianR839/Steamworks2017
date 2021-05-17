package org.usfirst.frc.team839.robot.subsystems;

import org.usfirst.frc.team839.robot.RobotMap;
import org.usfirst.frc.team839.robot.commands.HoodCommand;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Hood extends Subsystem
{

	int maxHoodPosition = 2262;
    public void initDefaultCommand() 
    {
       	setDefaultCommand(new HoodCommand());
    }
    public static void up()
    {
    	RobotMap.hood.set(1);
    }
    
    public static void down()
    {
    	RobotMap.hood.set(-1);
    }
   
    public void hood(double power)
    {
    	RobotMap.hood.set(power);
    }
    public void move(double power)
    {
    	RobotMap.hood.set(-power);
    }

    public void setPosition(double position)
    {
    	RobotMap.hood.changeControlMode(TalonControlMode.Position);
    	RobotMap.hood.set(position);
    }
    public void reSetPosition()
    {
    	RobotMap.hood.reset();
    }

    public void stop()
    {
    	RobotMap.hood.set(0);
    }
}
