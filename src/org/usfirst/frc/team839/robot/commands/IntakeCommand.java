package org.usfirst.frc.team839.robot.commands;

import org.usfirst.frc.team839.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * The command that allows the robot to shoot.
 * @author Matt
 */
public class IntakeCommand extends Command
{
    double power = 1;
	
    public IntakeCommand(double duration)
    {
    	this.setTimeout(duration);
    	this.power = 1;
    	requires(Robot.intake);
    }
    
    public IntakeCommand()
    {
    	requires(Robot.intake);
    }

    protected void initialize() 
    {

    }

    protected void execute() 
    {
        Robot.intake.intake();
    }

    protected boolean isFinished() 
    {
        return this.isTimedOut();
    }

    protected void end() 
    {
    	Robot.intake.stop();
    }

    protected void interrupted() 
    {
    	end();
    }
}