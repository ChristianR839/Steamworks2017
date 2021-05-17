package org.usfirst.frc.team839.robot.commands;

import org.usfirst.frc.team839.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ShooterSpeedCommand extends Command
{
    double power = .25;
	
    public ShooterSpeedCommand(double duration)
    {
    	this.setTimeout(duration);
    	this.power = 1;
    	requires(Robot.hood);
    }
    
    public ShooterSpeedCommand()
    {
    }

    protected void initialize() 
    {

    }

    protected void execute() 
    {
	    double y = Robot.oi.getHoodMovement();
        if(Math.abs(y) >= 0.1)
        {
        	if(y>0)
        		Robot.hood.move(power);
        	else	
        		Robot.hood.move(-power);
        }
        else
        {
        	Robot.hood.move(0);
        }      
        
   }

    protected boolean isFinished() 
    {
        return this.isTimedOut();
    }

    protected void end() 
    {
//    	Robot.hood.stop();
    }

    protected void interrupted() 
    {
    	end();
    }
}
