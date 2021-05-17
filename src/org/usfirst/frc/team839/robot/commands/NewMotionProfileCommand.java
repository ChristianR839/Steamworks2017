package org.usfirst.frc.team839.robot.commands;

import org.usfirst.frc.team839.robot.instrumentation;
//import org.usfirst.frc.team839.robot.util.OrigMotionProfileExample.PeriodicRunnable;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.command.Command;

public class NewMotionProfileCommand extends Command
{
	private CANTalon.MotionProfileStatus _status = new CANTalon.MotionProfileStatus();
	private CANTalon talon = new CANTalon(5);
	private CANTalon talon2 = new CANTalon(3);
	
	private int _state = 0;
	private int _loopTimeout = -1;
	private boolean _bStart = false;
	
	private CANTalon.SetValueMotionProfile _setValue = CANTalon.SetValueMotionProfile.Disable;
	private static final int kMinPointsInTalon = 5;
	private static final int kNumLoopsTimeout = 10;
	
	public class PeriodicRunnable implements java.lang.Runnable 
	{
	    public void run() 
	    {
	    	talon.processMotionProfileBuffer();
	    }
	}
	
	Notifier _notifer = new Notifier(new PeriodicRunnable());

	public NewMotionProfileCommand()
	{
		//This was originally set to 5 and 0.005
		talon.changeMotionControlFramePeriod(10);
		_notifer.startPeriodic(0.01);
	}
	@Override
	protected void initialize()
	{

	}
	@Override
	protected void execute()
	{
		talon.getMotionProfileStatus(_status);

		if (_loopTimeout < 0) 
		{
			//Do nothing
		} else 
		{
			if (_loopTimeout == 0) 
			{
				//Something is wrong with Talon
				instrumentation.OnNoProgress();
			} 
			else 
			{
				--_loopTimeout;
			}
		}

		//Check if we are in Motion Profiling mode
		if (talon.getControlMode() != TalonControlMode.MotionProfile) 
		{
			//This runs if we are NOT in Motion Profiling mode
			_state = 0;
			_loopTimeout = -1;
		} 
		else 
		{
			//This runs if we ARE in Motion Profiling mode
			switch (_state) 
			{
				case 0: //Wait for the application to tell us to start an MP
					if (_bStart) 
					{
						_bStart = false;
	
						_setValue = CANTalon.SetValueMotionProfile.Disable;
						//startFilling();
						//MP is being sent to CAN bus, wait a small amount of time
						_state = 1;
						_loopTimeout = kNumLoopsTimeout;
					}
					break;
				case 1: /*
						 * wait for MP to stream to Talon, really just the first few
						 * points
						 */
					/* do we have a minimum numberof points in Talon */
					if (_status.btmBufferCnt > kMinPointsInTalon) 
					{
						/* start (once) the motion profile */
						_setValue = CANTalon.SetValueMotionProfile.Enable;
						/* MP will start once the control frame gets scheduled */
						_state = 2;
						_loopTimeout = kNumLoopsTimeout;
					}
					break;
				case 2: /* check the status of the MP */
					/*
					 * if talon is reporting things are good, keep adding to our
					 * timeout. Really this is so that you can unplug your talon in
					 * the middle of an MP and react to it.
					 */
					if (_status.isUnderrun == false) 
					{
						_loopTimeout = kNumLoopsTimeout;
					}
					/*
					 * If we are executing an MP and the MP finished, start loading
					 * another. We will go into hold state so robot servo's
					 * position.
					 */
					if (_status.activePointValid && _status.activePoint.isLastPoint) 
{
						/*
						 * because we set the last point's isLast to true, we will
						 * get here when the MP is done
						 */
						_setValue = CANTalon.SetValueMotionProfile.Hold;
						_state = 0;
						_loopTimeout = -1;
					}
					break;
			}
		}
		/* printfs and/or logging */
		instrumentation.process(_status);
	}
	@Override
	protected boolean isFinished()
	{
		return false;
	}
	@Override
	protected void end()
	{

	}
	@Override
	protected void interrupted()
	{
		talon.clearMotionProfileTrajectories();
		_setValue = CANTalon.SetValueMotionProfile.Disable;
		_state = 0;
		_loopTimeout = -1;
		_bStart = false;
	}
}
