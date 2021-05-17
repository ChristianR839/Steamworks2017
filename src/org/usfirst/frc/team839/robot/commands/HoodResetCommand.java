package org.usfirst.frc.team839.robot.commands;

import org.usfirst.frc.team839.robot.Robot;
import org.usfirst.frc.team839.robot.RobotMap;
import org.usfirst.frc.team839.robot.subsystems.Hood;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;

public class HoodResetCommand extends Command
{
    double power = .25;
	
    public HoodResetCommand(double duration)
    {
    	this.setTimeout(duration);
    	this.power = 1;
    	requires(Robot.hood);
    }
    
    public HoodResetCommand()
    {
    }

    protected void initialize() 
    {
    }

    protected void execute() 
    {
    	System.out.println("put the hood down");
    	Hood.down();
   }

    protected boolean isFinished() 
    {
    	return false;
    }

    protected void end() 
    {
    	Robot.hood.stop();
    	RobotMap.hood.changeControlMode(TalonControlMode.Position);
    	RobotMap.hood.reset();
    }

    protected void interrupted() 
    {
    	end();
    }
}
