package org.usfirst.frc.team839.robot.commands;

import org.usfirst.frc.team839.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * The command that allows the robot to climb.
 * @author Matt
 */
public class ClimbCommand extends Command
{
	private double power = 1;

	public ClimbCommand()
    {
    	requires(Robot.climber);
    }
	
    public ClimbCommand(double duration)
    {
    	this.setTimeout(duration);
    	requires(Robot.climber);
    }

    protected void initialize() 
    {

    }

    protected void execute() 
    {
   		Robot.climber.climb(this.power);
    }

    protected boolean isFinished() 
    {
        return this.isTimedOut();
    }

    protected void end() 
    {
    	Robot.climber.stop();
    }

    protected void interrupted() 
    {
    	end();
    }
}
