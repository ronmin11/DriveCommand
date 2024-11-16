// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;
import edu.wpi.first.wpilibj.Encoder;




public class DriveTrainSubsystem extends SubsystemBase {
  
  /** Creates a new DriveTrainSubsystem. */
  public static CANSparkMax frontLeft;
  public static CANSparkMax frontRight;
  public static CANSparkMax backLeft;
  public static CANSparkMax backRight;
  private DifferentialDrive drive;
  Encoder encoder = new Encoder(0, 0); //Issue with encoder initialization


  public DriveTrainSubsystem() {
    // Use addRequirements() here to declare subsystem dependencies.
    frontLeft = new CANSparkMax(DriveConstants.FRONT_LEFT_ID, MotorType.kBrushless);
    frontRight = new CANSparkMax(DriveConstants.FRONT_RIGHT_ID, MotorType.kBrushless);
    backLeft = new CANSparkMax(DriveConstants.BACK_LEFT_ID, MotorType.kBrushless);
    backRight = new CANSparkMax(DriveConstants.BACK_RIGHT_ID, MotorType.kBrushless);
    
    drive = new DifferentialDrive(frontLeft,frontRight);

    configureMotors(frontLeft,null,true);
    configureMotors(frontRight,null,false);
    configureMotors(backLeft,frontLeft,true);
    configureMotors(backRight,frontRight,false);
    
  }

  public void arcadeDrive(double speed, double rotation){
    drive.arcadeDrive(speed, rotation); 
  }

  private void configureMotors(CANSparkMax motor, CANSparkMax follow, Boolean isInverted) {
    motor.restoreFactoryDefaults();
    motor.setInverted(isInverted);
    if (follow != null) {
      motor.follow(follow);
    }
   
    motor.setIdleMode(IdleMode.kBrake);
    motor.setSmartCurrentLimit(DriveConstants.CURRENT_LIMIT);
    motor.burnFlash();

    
  }


  public void periodic(){
    
  }

    public void setMotors(double leftSpeed, double rightSpeed) {
      
      frontLeft.set(leftSpeed);
      backLeft.set(leftSpeed);
      frontRight.set(-rightSpeed);
      backRight.set(-rightSpeed);

    }
    
    public double getDistance() {
      return encoder.getDistance();
    } 

}
