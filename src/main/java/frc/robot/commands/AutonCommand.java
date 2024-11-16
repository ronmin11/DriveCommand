package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveTrainSubsystem;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.Encoder;
import frc.robot.Constants.DriveConstants;

public class AutonCommand extends Command {
    private final DriveTrainSubsystem drive;
    private final ProfiledPIDController pidController;

    public AutonCommand(DriveTrainSubsystem drive, double targetDistance) {
        this.drive = drive;

        pidController = new ProfiledPIDController(
            //PID Constants
            DriveConstants.KP,
            DriveConstants.KI,
            DriveConstants.KD,
            new TrapezoidProfile.Constraints(DriveConstants.MAX_VELOCITY, DriveConstants.MAX_ACCELERATION));
        
            // Set the setpoint to the target distance
            pidController.setGoal(targetDistance);
            pidController.reset(drive.getDistance());
            pidController.setTolerance(5);
        
        addRequirements(drive);
    }

  
    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        double position = drive.getDistance(); //getDistance method in drive subsyst

        double output = pidController.calculate(position);

        drive.arcadeDrive(output, 0);
    }

    @Override
    public void end(boolean interrupted) {
        drive.arcadeDrive(0,0);
    }

    @Override
    public boolean isFinished() {
        return pidController.atGoal();
    }
    
    
}

    
    

