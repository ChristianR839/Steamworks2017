package org.usfirst.frc.team839.robot.commands;

import java.util.ArrayList;

import org.usfirst.frc.team839.robot.util.SCurve;
import org.usfirst.frc.team839.robot.util.SCurvePoint;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;

public class ProfilingMotion extends Command
{
	int currentPoint = 0;
	CANTalon talon = new CANTalon(5);
	CANTalon talon2 = new CANTalon(3);
	private static ArrayList<SCurvePoint> points;
	double period;
	double distance;
	double acceleration;
	private boolean finished = false;
	public ProfilingMotion(double period, double distance, double acceleration)
	{
		this.period = period;
		this.distance = distance;
		this.acceleration = acceleration;
	}
	protected void initialize()
	{
		talon.changeControlMode(TalonControlMode.Speed);
		talon2.changeControlMode(TalonControlMode.Speed);
		talon.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		talon.reverseSensor(false);
		talon.configEncoderCodesPerRev(1440);
		talon.configNominalOutputVoltage(+0.0f, -0.0f);
		talon.configPeakOutputVoltage(+12.0f, 0.0f);
		SCurve scurve = new SCurve();
		points = scurve.getSCurve(period, distance, acceleration);
		talon.setProfile(0);
		talon.setF(0.1097);
		talon.setP(0.22);
		talon.setI(0);
		talon.setD(0);
		talon.changeControlMode(TalonControlMode.Speed);
	}
	protected void execute() 
	{
		
		SCurvePoint point = points.get(currentPoint);
		if (point != null)
		{
			//talon.set(points.get(currentPoint).v);
			//talon2.set(points.get(currentPoint).v);
			//talon.set(point.v);
			//talon2.set(point.v);
			//Robot.drivetrain.setDriveSpeeds(point.v,point.v);
			talon.set(point.v);
			System.out.println(talon.get());
			currentPoint = currentPoint+1;
		}
		else 
			finished = true;
    }

    protected boolean isFinished() 
    {
        return points.size() <= currentPoint || finished;
    }

    protected void end() 
    {
    	talon.changeControlMode(TalonControlMode.Voltage);
    }

    protected void interrupted() 
    {
    	end();
    }
}
