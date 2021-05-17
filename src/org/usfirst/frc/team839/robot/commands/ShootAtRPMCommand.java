package org.usfirst.frc.team839.robot.commands;

import org.usfirst.frc.team839.robot.Robot;
import org.usfirst.frc.team839.robot.RobotMap;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;

/**
 * The command that allows the robot to shoot.
 * @author Matt
 */
public class ShootAtRPMCommand extends Command
{
    double rpm = 5000;
	
    public ShootAtRPMCommand(double rpm)
    {
    	this.rpm = rpm;
    	requires(Robot.shooter);
    }
    
    public ShootAtRPMCommand()
    {
    	requires(Robot.shooter);
    	requires(Robot.agitator);
    }

    protected void initialize() 
    {
    	RobotMap.shooter.changeControlMode(TalonControlMode.Speed);

    }

    protected void execute() 
    {
    	Robot.shooter.spin(this.rpm);
        Robot.agitator.agitate(.5);
    }

    protected boolean isFinished() 
    {
        return this.isTimedOut();
    }

    protected void end() 
    {
    	Robot.agitator.stop();
    	Robot.shooter.stop();
    	RobotMap.shooter.changeControlMode(TalonControlMode.Voltage);
    }

    protected void interrupted() 
    {
    	end();
    }
}