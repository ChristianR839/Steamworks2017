package org.usfirst.frc.team839.robot;


import org.usfirst.frc.team839.robot.commands.AgitatorCommand;
import org.usfirst.frc.team839.robot.commands.ClimbCommand;
import org.usfirst.frc.team839.robot.commands.GearHandlerInCommand;
import org.usfirst.frc.team839.robot.commands.GearHandlerOutCommand;
import org.usfirst.frc.team839.robot.commands.IntakeCommand;
import org.usfirst.frc.team839.robot.commands.ShootCommand;
import org.usfirst.frc.team839.robot.commands.autonomous.PlaceGear;

import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI 
{
	public UniversalJoystick joystickDrive = new UniversalJoystick(0);
	public UniversalJoystick joystickAccessory = new UniversalJoystick(1);
	
	public JoystickButton shootButton;
	private JoystickButton intakeButton;
	private JoystickButton agitatorButton;
	private JoystickButton climbButton;
//	private JoystickButton gearPusherButton;
	private JoystickButton gearOutButton;
	private JoystickButton gearInButton;
	private JoystickButton ledRingsButton;
	private JoystickButton tableShutdownButton;
	public Button accessoryShiftButton;
	public Button driveShiftButton;
	public Button fieldOrientedButton;

	public OI() 
	{
		//Accessory buttons
//    	shootButton             = new JoystickButton(joystickAccessory, UniversalJoystick.kBtnLB);
    	shootButton             = new JoystickButton(joystickAccessory, UniversalJoystick.kBtnX);
    	intakeButton            = new JoystickButton(joystickAccessory, UniversalJoystick.kBtnY);
//    	agitatorButton          = new JoystickButton(joystickAccessory, UniversalJoystick.kBtnRB);
    	agitatorButton          = new JoystickButton(joystickAccessory, UniversalJoystick.kBtnA);
    	climbButton             = new JoystickButton(joystickAccessory, UniversalJoystick.kBtnB);
    	//ledRingsButton          = new JoystickButton(joystickAccessory, UniversalJoystick.kBtnX);
    	accessoryShiftButton	= new JoystickButton(joystickAccessory, UniversalJoystick.kBtnModeA);
    	
		//Drive buttons
    	//tableShutdownButton     = new JoystickButton(joystickDrive, UniversalJoystick.kBtnStart);
//    	gearPusherButton		= new JoystickButton(joystickDrive, UniversalJoystick.kBtnRB);
    	gearOutButton		= new JoystickButton(joystickDrive, UniversalJoystick.kBtnRB);
    	gearInButton		= new JoystickButton(joystickDrive, UniversalJoystick.kBtnLB);
    	driveShiftButton		= new JoystickButton(joystickDrive, UniversalJoystick.kBtnStart);
    	fieldOrientedButton		= new JoystickButton(joystickDrive, UniversalJoystick.kBtnModeB);


    	shootButton              .whileHeld(new ShootCommand());
    	intakeButton             .whileHeld(new IntakeCommand());
    	agitatorButton           .whileHeld(new AgitatorCommand());
    	climbButton              .whileHeld(new ClimbCommand());
    	//gearPusherButton		 .whenPressed(new PlaceGear());
    	gearOutButton		 	 .whileHeld(new GearHandlerOutCommand());
    	gearInButton		 	 .whenPressed(new GearHandlerInCommand());
    	//ledRingsButton         .whileHeld(new LEDCommand());
    	//tableShutdownButton    .whenPressed(new NetworkTableSenderCommand("getDistance"));
    	//fieldOrientedButton	 .whenPressed(new ResetGyroCommand());
	}
	
	public UniversalJoystick getJoystickDrive() 
    {
        return joystickDrive;
    }
	
	public double getStrafeSpeed()
    {
    	return deadBand(joystickDrive.getX());
    }
    
    public double getSpeed()
    {
    	return deadBand(joystickDrive.getY());
    }

	public double getShooterSpeed()
    {
    	return Math.abs(joystickAccessory.getZ())*25000;
    }

	public double getHoodLocation()
    {
    	return joystickAccessory.getAxis(AxisType.kThrottle);
    }
   
    public double getRotation()
    {
    	return deadBand(joystickDrive.rightAxisX());//.getThrottle());
    }
    public double getHoodMovement()
    {
    	return deadBand(joystickAccessory.leftAxisY());
    	//return joystickAccessory.getThrottle();//.getThrottle());
    }
    public double getHoodPosition()
    {
    	return joystickAccessory.getThrottle();//.getThrottle());
    }
    double deadBand(double axisVal)
    {
    	if(axisVal < -0.150)//Orig: -0.250
    		return axisVal;
    	if(axisVal > +0.150)//Orig: +0.250
    		return axisVal;
    	return 0;
    }

}

