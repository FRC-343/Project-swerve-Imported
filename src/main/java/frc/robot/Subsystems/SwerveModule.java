package frc.robot.Subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.AnalogEncoder;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class SwerveModule extends SubsystemBase {

	private CANSparkMax angleMotor;
	private Spark speedMotor;
	private PIDController pidController;
	private RelativeEncoder angleEncoder;

	double setpoint;
	double setpointtest;

	private final double MAX_VOLTS = 4;

	public SwerveModule(CANSparkMax angleMotor, Spark speedMotor) {
		this.angleMotor = angleMotor;
		this.speedMotor = speedMotor; 
		
		angleEncoder = this.angleMotor.getEncoder();

		

		pidController = new PIDController(1, 0, 0);
		pidController.enableContinuousInput(-180, 180); // this needs to change later TODO
	}

	@Override
	public void periodic() {
		angleMotor.set(pidController.calculate(angleEncoder.getPosition())); // spins angle motor
	}

	public void drive(double speed, double angle) {
		speedMotor.set(speed);

		 setpoint = angle * (MAX_VOLTS * 0.5) + (MAX_VOLTS * 0.5);
		if (setpoint < 0) {
			setpointtest = MAX_VOLTS + setpoint;
		}
		if (setpoint > MAX_VOLTS) {
			setpointtest = setpoint - MAX_VOLTS;
		}
		if (setpoint == 2){
			setpointtest = 0;
		}
		else {
			setpointtest = setpoint;
		}

		pidController.setSetpoint(setpointtest); // point to spin angle motor
	}

    public void set(SwerveModule backRight, SwerveModule backLeft, SwerveModule frontRight,
            SwerveModule frontLeft) {
    }

}
