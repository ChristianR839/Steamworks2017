package org.usfirst.frc.team839.robot.commands;

import org.usfirst.frc.team839.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class FullShotCommand extends Command
{
    double power = 1;
	
    public FullShotCommand(double duration)
    {
    	this.setTimeout(duration);
    	this.power = 1;
    	requires(Robot.shooter);

    }
    
    public FullShotCommand()
    {
    	requires(Robot.shooter);
    }

    protected void initialize() 
    {

    }

    protected void execute() 
    {
       // Robot.shooter.shoot(this.power);
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
