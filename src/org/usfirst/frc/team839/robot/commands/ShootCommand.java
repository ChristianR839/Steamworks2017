package org.usfirst.frc.team839.robot.commands;

import org.usfirst.frc.team839.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * The command that allows the robot to shoot.
 * @author Matt
 */
public class ShootCommand extends Command
{
    double power = .78;
	
    public ShootCommand(double power)
    {
    	this.setTimeout(power);
    	this.power = power;
    	requires(Robot.shooter);
    }
    
    public ShootCommand()
    {
    	//requires(Robot.shooter);
    }

    protected void initialize() 
    {

    }

    protected void execute() 
    {
    	Robot.shooter.spin(Robot.oi.getShooterSpeed());
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