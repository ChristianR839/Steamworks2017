package org.usfirst.frc.team839.robot.commands;

import org.usfirst.frc.team839.robot.GeneratedMotionProfile;
import org.usfirst.frc.team839.robot.instrumentation;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;

public class MotionProfileCommand extends Command
{
	CANTalon _talon = new CANTalon(3);
	//MotionProfileExample _example = new MotionProfileExample(_talon);
	private CANTalon.MotionProfileStatus _status = new CANTalon.MotionProfileStatus();
	private int _state = 0;
	private int _loopTimeout = -1;
	private boolean _bStart = false;
	private CANTalon.SetValueMotionProfile _setValue = CANTalon.SetValueMotionProfile.Disable;
	private static final int kMinPointsInTalon = 5;
	private static final int kNumLoopsTimeout = 10;
	
	@Override
	protected void initialize()
	{
		_talon.clearMotionProfileTrajectories();
		_setValue = CANTalon.SetValueMotionProfile.Disable;
		_state = 0;
		_loopTimeout = -1;
		_bStart = true;
		_talon.changeControlMode(TalonControlMode.MotionProfile);
		//_example.startMotionProfile();
		_talon.set(CANTalon.SetValueMotionProfile.Enable.value);
	}
	private void startFilling() {
		/* since this example only has one talon, just update that one */
		startFilling(GeneratedMotionProfile.Points, GeneratedMotionProfile.kNumPoints);
	}

	private void startFilling(double[][] profile, int totalCnt) {

		/* create an empty point */
		CANTalon.TrajectoryPoint point = new CANTalon.TrajectoryPoint();

		/* did we get an underrun condition since last time we checked ? */
		if (_status.hasUnderrun) {
			/* better log it so we know about it */
			instrumentation.OnUnderrun();
			/*
			 * clear the error. This flag does not auto clear, this way 
			 * we never miss logging it.
			 */
			_talon.clearMotionProfileHasUnderrun();
		}
		for (int i = 0; i < totalCnt; ++i) {
			/* for each point, fill our structure and pass it to API */
			point.position = profile[i][0];
			point.velocity = profile[i][1];
			point.timeDurMs = (int) profile[i][2];
			point.profileSlotSelect = 0; /* which set of gains would you like to use? */
			point.velocityOnly = false; /* set true to not do any position
										 * servo, just velocity feedforward
										 */
			point.zeroPos = false;
			if (i == 0)
				point.zeroPos = true; /* set this to true on the first point */

			point.isLastPoint = false;
			if ((i + 1) == totalCnt)
				point.isLastPoint = true; /* set this to true on the last point  */

			_talon.pushMotionProfileTrajectory(point);
		}
	}
	
	public void startMotionProfile() {
		_bStart = true;
	}
	
	@Override
	protected void execute()
	{

			_talon.getMotionProfileStatus(_status);
			if (_loopTimeout < 0) 
			{
				
			} 
			else 
			{
				if (_loopTimeout == 0) 
				{
					instrumentation.OnNoProgress();
				} 
				else 
				{
					--_loopTimeout;
				}
			}
			if (_talon.getControlMode() != TalonControlMode.MotionProfile)
			{	
				_state = 0;
				_loopTimeout = -1;
			} 
			else 
			{
				switch (_state) 
				{
				case 0: 
					if (_bStart) 
					{
						_bStart = false;
						_setValue = CANTalon.SetValueMotionProfile.Disable;
						startFilling();
						_state = 1;
						_loopTimeout = kNumLoopsTimeout;
					}
					break;
					
				case 1: 
					if (_status.btmBufferCnt > kMinPointsInTalon) 
					{
						_setValue = CANTalon.SetValueMotionProfile.Enable;	
						_state = 2;
						_loopTimeout = kNumLoopsTimeout;

					}
					break;
					
				case 2: 
					if (_status.isUnderrun == false) 
					{
						_loopTimeout = kNumLoopsTimeout;
					}
					
					if (_status.activePointValid && _status.activePoint.isLastPoint) 
					{
						_setValue = CANTalon.SetValueMotionProfile.Hold;
						_state = 0;
						_loopTimeout = -1;
					}
					break;
				}
			}
			_talon.processMotionProfileBuffer();
			instrumentation.process(_status);
		}

	@Override
	protected boolean isFinished()
	{
		return _setValue == CANTalon.SetValueMotionProfile.Hold;
	}

	@Override
	protected void end()
	{
		
	}

	@Override
	protected void interrupted()
	{
		
	}
}
