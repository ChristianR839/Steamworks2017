package org.usfirst.frc.team839.robot.commands.autonomous;

import org.usfirst.frc.team839.robot.commands.DriveCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveALittle extends CommandGroup
{
	{
	//CommandGroup group = new CommandGroup();
	//group.addParallel (new );
	//group.addParallel (new );
	
	//Drive outwards
	addSequential (new DriveCommand(16,.5, 0,0));
	//Strafe to hopper
	addSequential (new DriveCommand(0,0, 0,0));
	//Drive to hopper
	addSequential (new DriveCommand(0,0, 0,0));

	}
}
