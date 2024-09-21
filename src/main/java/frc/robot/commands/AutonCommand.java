package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveTrainSubsystem;

public class AutonCommand extends Command{

    private DriveTrainSubsystem drive;
    private double distance;

    private double counter = 0;
    private double target = 0;
    
    

    public AutonCommand(double distance,DriveTrainSubsystem drive, double seconds) {
        this.distance = drive.getEncoderMeters() + distance;
        this.drive = drive;


        target = (int)(seconds * 50);
        addRequirements(drive);
    }

    @Override
    public void initialize() {
        System.out.println("Autonomous init");

    }

    @Override
    public void execute() {
        System.out.println("Autonomous execute");
        if (counter < target) {
            counter++;
            if (counter < target/2) {
                drive.setMotors(-0.1, 0.1);
            }
        }
        drive.setMotors(0.1,-0.1);
    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("auton end");

        drive.setMotors(0,0);
    }

    @Override
    public boolean isFinished() {
        return counter >= target;
    }
}

    
    

