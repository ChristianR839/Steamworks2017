package org.usfirst.frc.team839.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class NetworkTableSenderCommand extends Command
{
	NetworkTable table;
	private String command;
	
	public NetworkTableSenderCommand(String command)
	{
		this.command = command;
	}
	
    protected void initialize()
    {
    	table = NetworkTable.getTable("visionTable");
    }
    
    @Override
    protected void execute()
    {
		table.putString("command", this.command);
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
    	end();
    }
}