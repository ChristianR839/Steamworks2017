package org.usfirst.frc.team839.robot.subsystems;

import org.usfirst.frc.team839.robot.RobotMap;
import org.usfirst.frc.team839.robot.commands.DriveWithJoystick;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The drivetrain subsystem for the robot.
 * @author Matt
 */
public class DriveTrain extends Subsystem
{

    public void initDefaultCommand() 
    {
        setDefaultCommand(new DriveWithJoystick());
    }
     
    /**
     * Sets the drive speeds for the Mecanum drive.
     *
     * @param strafe The speed that the robot should drive in the X direction. [-1.0..1.0]
     * @param forward The speed that the robot should drive in the Y direction. [-1.0..1.0]
     * @param rotate The rate of rotation for the robot. [-1.0..1.0]
     * @param angle The current angle reading from the gyro.
     */
    public void setDriveSpeeds(double strafe, double forward, double rotate, double angle)
    {
    	RobotMap.robotDrive.mecanumDrive_Cartesian(strafe, forward, rotate, angle);
    	RobotMap.robotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
    	RobotMap.robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
	
    }
    
    public void stop()
    {
    	RobotMap.robotDrive.mecanumDrive_Cartesian(0, 0, 0, 0);
    }

}
