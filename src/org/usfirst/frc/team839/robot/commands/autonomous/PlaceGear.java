package org.usfirst.frc.team839.robot.commands.autonomous;

import org.usfirst.frc.team839.robot.commands.GearHandlerInCommand;
import org.usfirst.frc.team839.robot.commands.GearHandlerOutCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PlaceGear extends CommandGroup
{
	{
	//CommandGroup group = new CommandGroup();
	//group.addParallel (new );
	//group.addParallel (new );

	//addSequential (new );
		//addSequential (new DriveCommand(0,0,0));
		addSequential (new GearHandlerOutCommand());
		addSequential (new GearHandlerInCommand());

	}
}