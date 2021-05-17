package org.usfirst.frc.team839.robot.commands;

import org.usfirst.frc.team839.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Agitate extends Command
{
    double power = .5;
	
    public Agitate(double duration)
    {
    	this.setTimeout(duration);
    	requires(Robot.shooter);
    	requires(Robot.agitator);
    }
    
    public Agitate()
    {
    	requires(Robot.shooter);
    	requires(Robot.agitator);
    }

    protected void initialize() 
    {

    }

    protected void execute() 
    {
    	Robot.agitator.agitate(this.power);

    }

    protected boolean isFinished() 
    {
        return this.isTimedOut();
    }

    protected void end() 
    {

    	Robot.agitator.stop();
    }

    protected void interrupted() 
    {
    	end();
    }
}
