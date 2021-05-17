package org.usfirst.frc.team839.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap 
{
	private static final int frontRightMotorID = 5;
	private static final int frontLeftMotorID = 2;
	private static final int backRightMotorID = 3;
	private static final int backLeftMotorID = 4;
	
	private static final int shooterMotorID = 13;
	private static final int agitatorMotorID = 12;
	private static final int intakeMotorID = 14;
	private static final int hoodMotorID = 11;

	private static final int climberPWMChannel = 0;

	public static final CANTalon frontRightMotor = new CANTalon(frontRightMotorID);
	public static final CANTalon frontLeftMotor = new CANTalon(frontLeftMotorID);
	public static final CANTalon backRightMotor = new CANTalon(backRightMotorID);
	public static final CANTalon backLeftMotor = new CANTalon(backLeftMotorID);
	
	public static final CANTalon shooter = new CANTalon(shooterMotorID);
	public static final CANTalon agitator = new CANTalon(agitatorMotorID);
	public static final CANTalon intake = new CANTalon(intakeMotorID);
	public static final CANTalon hood = new CANTalon(hoodMotorID);
	
	public static final Talon climber = new Talon(climberPWMChannel);




	public static final RobotDrive robotDrive = new RobotDrive(frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor);
	public static Solenoid lightRing = new Solenoid(60,0);
	public static DoubleSolenoid gearHandlerL = new DoubleSolenoid(60, 2,1);
	//public static DoubleSolenoid gearHandlerR = new DoubleSolenoid(60, 2, 3);
	public static Compressor compressor = new Compressor(60);
	public static Joystick joystickDrive;
	public static Joystick joystickAccessory;
	
	//public static AHRS gyro;
	
	private static final int refreshrate = 60;
	
	public static void init()
	{
		compressor.clearAllPCMStickyFaults();
		compressor.start();
		gearHandlerL.set(DoubleSolenoid.Value.kReverse);
		
		frontRightMotor.setVoltageRampRate(18);
		frontLeftMotor.setVoltageRampRate(18);
		backRightMotor.setVoltageRampRate(18);
		backLeftMotor.setVoltageRampRate(18);

		frontLeftMotor.setInverted(true);
		backLeftMotor.setInverted(true);

		joystickDrive = new Joystick(0);
		joystickAccessory = new Joystick(1);
		
		//gyro = new AHRS(SPI.Port.kMXP, (byte)refreshrate);
		
	}
}
