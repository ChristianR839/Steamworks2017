package org.usfirst.frc.team839.robot.util;

import java.util.ArrayList;

public class SCurve 
{
	private static double a(double time, double acceleration, double K1)
	{
		double acc = acceleration*Math.sin(K1*time);
		return acc;
	}
	private static double v(double time, double K1, double K2)
	{
		double vel = K2*(1-Math.cos(K1*time));
		return vel;
	}
	private static double x(double time, double K1, double K2, double K3)
	{
		double pos = K2*(time-K3*Math.sin(K1*time));
		return pos;
	}
	
	public static ArrayList<SCurvePoint> getSCurve(double period, double distance, double acceleration)
	{
		double T = Math.sqrt((2*(Math.PI)*distance)/acceleration);
		double K1 = (2*(Math.PI))/T;
		double K2 = acceleration/K1;
		double K3 = 1/K1;
		
		SCurvePoint point = new SCurvePoint();
		ArrayList<SCurvePoint> points = new ArrayList<SCurvePoint>();
		int pointcounter = 0;
		while((point.x = x(pointcounter*period, K1, K2, K3)) <= distance){
			point.a = a(pointcounter*period, acceleration, K1);
			point.v = v(pointcounter*period, K1, K2);
			
			points.add(point);
			pointcounter++;
			point = new SCurvePoint();
		}
			
		return points;
	}
	public static void main(String[] args) {
		ArrayList<SCurvePoint> points = SCurve.getSCurve(.02, 10,50);
		for (int i = 0; i < points.size(); i++) {
			System.out.println(points.get(i).toString());
			
		}
	}
}