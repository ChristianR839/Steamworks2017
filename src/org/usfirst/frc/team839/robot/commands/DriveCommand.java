package org.usfirst.frc.team839.robot.commands;

import org.usfirst.frc.team839.robot.Robot;
import org.usfirst.frc.team839.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class DriveCommand extends Command
{

    private double inches;
	private double forwardSpeed = 0;
	DriveTrain drivetrain = Robot.drivetrain;
	private double strafeSpeed = 0;
	
	public DriveCommand(double inches, double speed, double strafeSpeed, double angle) 
    {
        
        this.setTimeout(5);
		

		this.inches = inches;
		this.forwardSpeed = speed;
		this.strafeSpeed  = strafeSpeed;
    }

    protected void initialize() 
    {
    	Robot.gyro.reset();
    }

    protected void execute() 
    {
    	drivetrain.setDriveSpeeds(this.strafeSpeed, -this.forwardSpeed, 0, 0);
    }

    protected boolean isFinished() 
    {
        return this.isTimedOut();
    }

    protected void end() 
    {
    	drivetrain.stop();
    }

    protected void interrupted() 
    {
    	end();
    }
}
