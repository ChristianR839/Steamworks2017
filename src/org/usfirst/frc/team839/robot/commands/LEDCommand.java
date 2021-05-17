package org.usfirst.frc.team839.robot.commands;

import org.usfirst.frc.team839.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class LEDCommand extends Command
{
	public LEDCommand()
    {
		
    }

    protected void initialize() 
    {

    }

    protected void execute() 
    {
    	Robot.lightring.toggle();
    }

    protected boolean isFinished() 
    {
    	return true;
    }

    protected void end() 
    {

    }

    protected void interrupted() 
    {
    	
    }
}
