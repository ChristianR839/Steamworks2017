package org.usfirst.frc.team839.robot.subsystems;

import org.usfirst.frc.team839.robot.RobotMap;

import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The shooter subsystem for the robot.
 * @author Matt
 */
public class Shooter extends Subsystem
{

	StringBuilder _sb = new StringBuilder();
	int _loops = 0;
	
    public void init(){
    	RobotMap.shooter.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
    	RobotMap.shooter.reverseSensor(false);
    	
    	RobotMap.shooter.configNominalOutputVoltage(+0.0f, -0.0f);
    	RobotMap.shooter.configPeakOutputVoltage(+12.0f, 0.0f);
    	
    	RobotMap.shooter.setProfile(0);
    	RobotMap.shooter.setF(0.03996);
    	RobotMap.shooter.setP(.1);
    	RobotMap.shooter.setI(0);
    	RobotMap.shooter.setD(0);
    	
       	RobotMap.shooter.changeControlMode(TalonControlMode.Speed);
    }
    public void initDefaultCommand() 
    {
 
    }
    
    public void spin(double targetSpeed)
    {
    
	/* get gamepad axis */
	double motorOutput = RobotMap.shooter.getOutputVoltage() / RobotMap.shooter.getBusVoltage();
	/* prepare line to print */
	_sb.append("\tout:");
	_sb.append(motorOutput);
    _sb.append("\tspd:");
    _sb.append(RobotMap.shooter.getSpeed() );
    

    	/* Speed mode */
   		RobotMap.shooter.changeControlMode(TalonControlMode.Speed);
   		RobotMap.shooter.enable();
    	RobotMap.shooter.set(targetSpeed); /* 1500 RPM in either direction */

    	/* append more signals to print when in speed mode. */
        _sb.append("\terr:");
        _sb.append(RobotMap.shooter.getClosedLoopError());
        _sb.append("\ttrg:");
        _sb.append(targetSpeed);


    if(++_loops >= 10) {
    	_loops = 0;
    	System.out.println(_sb.toString());
    }
    _sb.setLength(0);
}

   
    public void stop()
    {
    	RobotMap.shooter.disable();
    }
}