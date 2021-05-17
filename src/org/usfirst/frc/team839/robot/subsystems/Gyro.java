package org.usfirst.frc.team839.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.kauailabs.navx.frc.AHRS.SerialDataType;

import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The gyro subsystem for the robot.
 * @author Matt
 */
public class Gyro extends Subsystem 
{
	private static final int refreshrate = 60;
	
	private static final AHRS gyro = new AHRS(Port.kMXP, /*SerialPort.Port.kUSB, SerialDataType.kProcessedData,*/ (byte)refreshrate);

	protected void initDefaultCommand() 
	{
			
	}
	
	public double getAngle()
	{

		return gyro.getAngle();
	}
	
	public void reset()
	{
		gyro.reset();
	}
}
