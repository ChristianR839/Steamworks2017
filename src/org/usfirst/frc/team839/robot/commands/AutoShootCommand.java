package org.usfirst.frc.team839.robot.commands;

import org.usfirst.frc.team839.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * The command that allows the robot to shoot.
 * @author Matt
 */
public class AutoShootCommand extends Command
{
    double velocity = 22000;
	
    public AutoShootCommand(double power, double duration)
    {
    	this.setTimeout(duration);
    	this.velocity = power;
    }
    
    public AutoShootCommand()
    {

    }

    protected void initialize() 
    {

    }

    protected void execute() 
    {
    	Robot.shooter.spin(this.velocity);
    }

    protected boolean isFinished() 
    {
        return this.isTimedOut();
    }

    protected void end() 
    {
    	Robot.shooter.stop();
    }

    protected void interrupted() 
    {
    	end();
    }
}