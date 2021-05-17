package org.usfirst.frc.team839.robot.commands;

import org.usfirst.frc.team839.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class ResetGyroCommand extends InstantCommand {

    public ResetGyroCommand() {
        super();
        // Use requires() here to declare subsystem dependencies
    }

    // Called once when the command executes
    protected void initialize() {
    	Robot.gyro.reset();
    }

}
