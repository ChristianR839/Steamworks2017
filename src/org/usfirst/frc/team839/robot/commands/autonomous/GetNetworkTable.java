package org.usfirst.frc.team839.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;

public class GetNetworkTable extends Command implements ITableListener
{
	NetworkTable table;
	double distance;
	double angle;
	boolean isFinished = false;
	
    protected void initialize()
    {
    	table = NetworkTable.getTable("visionTable");
	table.addTableListener("distance", this, true);
	table.putString("command", "getDistance");
    }
    
    @Override
    protected void execute()
    {
    }

    @Override
    protected boolean isFinished()
    {
    	return isFinished;
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

	@Override
	public void valueChanged(ITable source, String key, Object value, boolean isNew) 
	{
		distance = ((Double)value).doubleValue(); 
		angle = source.getNumber("angle", 0);
		isFinished = true;
	}
}
