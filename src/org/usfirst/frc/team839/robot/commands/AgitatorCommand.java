package org.usfirst.frc.team839.robot.commands;

import org.usfirst.frc.team839.robot.Robot;
import org.usfirst.frc.team839.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class AgitatorCommand extends Command
{
double power = .3;
	
    public AgitatorCommand(double duration)
    {
    	this.setTimeout(duration);
    	//this.power = 1;
    	requires(Robot.agitator);
    }
    
    public AgitatorCommand()
    {
    	requires(Robot.agitator);
    }

    protected void initialize() 
    {

    }

    protected void execute() 
    {
    	if(Math.abs(RobotMap.shooter.getClosedLoopError())< 100){
	    	if(Robot.oi.accessoryShiftButton.get())
	    		Robot.agitator.agitate(-this.power);
	    	else
	    		Robot.agitator.agitate(this.power);
    	}
    	else
    		Robot.agitator.agitate(0);
    }

    protected boolean isFinished() 
    {
        return Math.abs(RobotMap.shooter.getEncVelocity())<.1;
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
