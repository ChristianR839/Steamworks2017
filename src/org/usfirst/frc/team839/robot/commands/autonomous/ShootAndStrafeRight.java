package org.usfirst.frc.team839.robot.commands.autonomous;

import org.usfirst.frc.team839.robot.commands.AgitatorCommand;
import org.usfirst.frc.team839.robot.commands.AutoShootCommand;
import org.usfirst.frc.team839.robot.commands.DriveCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class ShootAndStrafeRight extends CommandGroup
{
	{
		CommandGroup agitategroup = new CommandGroup();
		agitategroup.addSequential (new WaitCommand(1) );
		agitategroup.addSequential (new AgitatorCommand(5) );
		
		CommandGroup shootgroup = new CommandGroup();
		shootgroup.addParallel (new AutoShootCommand(.8, 7));
		shootgroup.addParallel (agitategroup );
		
	//shoot
		addSequential (shootgroup);
	//Strafe to hopper
		addSequential (new DriveCommand(16,0,-.5,0));


	}
}
