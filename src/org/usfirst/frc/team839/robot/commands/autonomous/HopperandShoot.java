package org.usfirst.frc.team839.robot.commands.autonomous;

import org.usfirst.frc.team839.robot.commands.AutoShootCommand;
import org.usfirst.frc.team839.robot.commands.DriveCommand;
import org.usfirst.frc.team839.robot.commands.GoodMotionProfileCommand;
import org.usfirst.frc.team839.robot.util.SCurve;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class HopperandShoot extends CommandGroup
{
	{
	//CommandGroup group = new CommandGroup();
	//group.addParallel (new );
	//group.addParallel (new );
	
	//Drive outwards
	addSequential (new GoodMotionProfileCommand(SCurve.getSCurve(.02, 10,50), GoodMotionProfileCommand.Direction.BACKWARD));
	//Strafe to hopper
	addSequential (new GoodMotionProfileCommand(SCurve.getSCurve(.02, 10,50), GoodMotionProfileCommand.Direction.STRAFE_LEFT));
	//Drive to hopper
	addSequential (new GoodMotionProfileCommand(SCurve.getSCurve(.02, 10,50), GoodMotionProfileCommand.Direction.FORWARD));
	//shoot
	addSequential (new AutoShootCommand(22000, 20));
	}
}
