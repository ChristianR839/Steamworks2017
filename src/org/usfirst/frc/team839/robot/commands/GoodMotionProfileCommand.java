package org.usfirst.frc.team839.robot.commands;

import java.util.ArrayList;

import org.usfirst.frc.team839.robot.RobotMap;
import org.usfirst.frc.team839.robot.instrumentation;
import org.usfirst.frc.team839.robot.util.SCurvePoint;

import com.ctre.CANTalon;
import com.ctre.CANTalon.MotionProfileStatus;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;

public class GoodMotionProfileCommand extends Command
{

	CANTalon _talon;
	CANTalon _talonSlave;
	CANTalon _talon2;
	CANTalon _talonSlave2;
	
	public static enum Direction {
	    FORWARD, BACKWARD, STRAFE_LEFT, STRAFE_RIGHT 
	};
	//MotionProfileExample _example = new MotionProfileExample(_talon);
	private CANTalon.MotionProfileStatus _status = new CANTalon.MotionProfileStatus();
	private CANTalon.MotionProfileStatus _status2 = new CANTalon.MotionProfileStatus();

	boolean isFinished = false;
	ArrayList<SCurvePoint> profile;
	private Direction direction = Direction.FORWARD;
	public GoodMotionProfileCommand(ArrayList<SCurvePoint> points, Direction direction )
	{
		System.out.println("GoodMotionProfileCommand constructor");
		
		this.profile = points;
		this.direction  = direction;
	}

	@Override
	protected void initialize()
	{
		System.out.println("GoodMotionProfileCommand initialize");
	    _talon = RobotMap.backRightMotor;
	    _talonSlave = RobotMap.frontRightMotor;
	    _talon2 = RobotMap.frontLeftMotor;
	    _talonSlave2 = RobotMap.backLeftMotor;

		switch (this.direction) {
		case BACKWARD:
		    _talon.setInverted(true);
		    _talonSlave.setInverted(true);
		    _talon2.setInverted(false);
		    _talonSlave2.setInverted(false);

		    break;
		case STRAFE_LEFT:
		    _talon.setInverted(false);
		    _talonSlave.setInverted(true);
		    _talon2.setInverted(false);
		    _talonSlave2.setInverted(true);
		    
		    break;
		case STRAFE_RIGHT:
		    _talon.setInverted(false);
		    _talonSlave.setInverted(true);
		    _talon2.setInverted(true);
		    _talonSlave2.setInverted(false);
		    
		    break;

		default:
		    _talon.setInverted(false);
		    _talonSlave.setInverted(false);
		    _talon2.setInverted(true);
		    _talonSlave2.setInverted(true);

		    break;
		}

		_talon.clearMotionProfileTrajectories();
		_talon.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
		_talon.reverseSensor(false);
		_talon.changeControlMode(TalonControlMode.MotionProfile);
		_talonSlave.changeControlMode(TalonControlMode.Follower);
		_talonSlave.set(_talon.getDeviceID());

		_talon2.clearMotionProfileTrajectories();
		_talon2.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
		_talon2.reverseSensor(false);
		_talon2.changeControlMode(TalonControlMode.MotionProfile);
		_talonSlave2.changeControlMode(TalonControlMode.Follower);
		_talonSlave2.set(_talon2.getDeviceID());

		_talon.setProfile(0);
		_talon.setF(0.1249);
		_talon.setP(0.22);
		_talon.setI(0);
		_talon.setD(0);
		
		_talon.set(CANTalon.SetValueMotionProfile.Disable.value);
		_talon.changeMotionControlFramePeriod(10);

		_talon2.setProfile(0);
		_talon2.setF(0.1249);
		_talon2.setP(0.22);
		_talon2.setI(0);
		_talon2.setD(0);
		
		_talon2.set(CANTalon.SetValueMotionProfile.Disable.value);
		_talon2.changeMotionControlFramePeriod(10);
		
		
		//feed Trajectory Points
		fill(profile,profile.size());
		_talon.set(CANTalon.SetValueMotionProfile.Enable.value);
		_talon2.set(CANTalon.SetValueMotionProfile.Enable.value);
		
		//		_talon.processMotionProfileBuffer();
	}

	private void fill(ArrayList<SCurvePoint> profile, int totalCnt) {

		System.out.println("GoodMotionProfileCommand fill");
		/* create an empty point */
		CANTalon.TrajectoryPoint point = new CANTalon.TrajectoryPoint();

		for (int i = 0; i < totalCnt; ++i) {
			/* for each point, fill our structure and pass it to API */
			point.position = profile.get(i).x;
			point.velocity = profile.get(i).v;
			point.timeDurMs = 20;
			point.profileSlotSelect = 0; /* which set of gains would you like to use? */
			point.velocityOnly = true; /* set true to not do any position servo, just velocity feedforward */
			point.zeroPos = false;
			if (i == 0)
				point.zeroPos = true; /* set this to true on the first point */

			point.isLastPoint = false;
			if ((i + 1) == totalCnt)
				point.isLastPoint = true; /* set this to true on the last point  */

			_talon.pushMotionProfileTrajectory(point);
			_talon2.pushMotionProfileTrajectory(point);
		}
	}
	

	
	@Override
	protected void execute()
	{
		_talon.getMotionProfileStatus(_status);
		_talon2.getMotionProfileStatus(_status2);

		if(_status.hasUnderrun)
		{
			System.out.println("GoodMotionProfileCommand _status.hasUnderrun");

			//load points
			_talon.clearMotionProfileHasUnderrun();;
			_talon.processMotionProfileBuffer();
		}
		if(_status2.hasUnderrun)
		{
			System.out.println("GoodMotionProfileCommand _status2.hasUnderrun");

			//load points
			_talon2.clearMotionProfileHasUnderrun();;
			_talon2.processMotionProfileBuffer();
		}
		
		if (_status.activePointValid && _status.activePoint.isLastPoint)
		{
			System.out.println("GoodMotionProfileCommand  _status.activePoint.isLastPoint");
			_talon.set(CANTalon.SetValueMotionProfile.Hold.value);
			isFinished = true;

		}
		else
		{
			instrumentation.process(_status);
			_talon.set(CANTalon.SetValueMotionProfile.Enable.value);
			_talon.processMotionProfileBuffer();
		}
		
		if (_status2.activePointValid && _status2.activePoint.isLastPoint)
		{
			System.out.println("GoodMotionProfileCommand  _status2.activePoint.isLastPoint");
			_talon2.set(CANTalon.SetValueMotionProfile.Hold.value);
			isFinished = true;

		}
		else
		{
			instrumentation.process(_status2);
			_talon2.set(CANTalon.SetValueMotionProfile.Enable.value);
			_talon2.processMotionProfileBuffer();
		}

	}
	@Override
	protected boolean isFinished()
	{
		return isFinished;
	}

	@Override
	protected void end()
	{
		_talon.set(CANTalon.SetValueMotionProfile.Hold.value);
		_talon.clearMotionProfileTrajectories();
		_talon.set(CANTalon.SetValueMotionProfile.Disable.value);
		_talon.changeControlMode(TalonControlMode.PercentVbus);
		_talonSlave.changeControlMode(TalonControlMode.PercentVbus);

		_talon2.set(CANTalon.SetValueMotionProfile.Hold.value);
		_talon2.clearMotionProfileTrajectories();
		_talon2.set(CANTalon.SetValueMotionProfile.Disable.value);
		_talon2.changeControlMode(TalonControlMode.PercentVbus);
		_talonSlave2.changeControlMode(TalonControlMode.PercentVbus);

		RobotMap.frontRightMotor.reverseOutput(false);
		RobotMap.backRightMotor.reverseOutput(false);
		RobotMap.backLeftMotor.reverseOutput(true);
		RobotMap.frontLeftMotor.reverseOutput(true);
		
		
		System.out.println("GoodMotionProfileCommand end");
	}

	@Override
	protected void interrupted()
	{
		
	}
}
