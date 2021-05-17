package org.usfirst.frc.team839.robot.commands;

import org.usfirst.frc.team839.robot.Robot;
import org.usfirst.frc.team839.robot.RobotMap;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;

public class HoodCommand extends Command
{
    double power = .25;
	/*
        position code

        RobotMap.hood.changeControlMode(ControlMode.Position); //Change control mode of talon, default is PercentVbus (-1.0 to 1.0)
	RobotMap.hood.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Absolute); //Set the feedback device that is hooked up to the talon
    
        RobotMap.hood.setPID(0.5, 0.001, 0.00, 0.00, 360, 36, 0); //Set the PID constants (p, i, d)
        
        RobotMap.hood.enableControl(); //Enable PID control on the talon
        int currentPosition = Robot.hood.getEncPosition();
        System.out.println(currentPosition);
        RobotMap.hood.set( requestedPosition); 




    */
    public HoodCommand(double duration)
    {
    	this.setTimeout(duration);
    	this.power = 1;
    	requires(Robot.hood);
    }
    
    public HoodCommand()
    {
    	requires(Robot.hood);

    }

    protected void initialize() 
    {
    	//RobotMap.hood.changeControlMode(TalonControlMode.Position);
    }

    protected void execute() 
    {
    	//System.out.println(RobotMap.hood.getEncPosition());
//	    double y = Robot.oi.getHoodPosition();
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
//    	Robot.hood.setPosition();
        
   }

    protected boolean isFinished() 
    {
        return this.isTimedOut();
    }

    protected void end() 
    {
    	Robot.hood.stop();
    }

    protected void interrupted() 
    {
    	end();
    }
}
