package org.usfirst.frc.team839.robot.commands.autonomous;

import org.usfirst.frc.team839.robot.commands.AutoShootCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class JustShoot extends CommandGroup
{
	{
	//CommandGroup group = new CommandGroup();
	//group.addParallel (new );
	//group.addParallel (new );

	addSequential (new AutoShootCommand(.5, 5));
	}
}