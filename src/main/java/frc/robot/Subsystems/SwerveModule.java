package frc.robot.Subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.AnalogEncoder;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SwerveModule extends SubsystemBase {

	private Spark angleMotor;
	private Spark speedMotor;
	private PIDController pidController;
	private AnalogEncoder angleEncoder;

	private final double MAX_VOLTS = 4;

	public SwerveModule(int angleMotor, int speedMotor, int encoder1) {
		this.angleMotor = new Spark(angleMotor);
		this.speedMotor = new Spark(speedMotor);
		angleEncoder = new AnalogEncoder(encoder1);

		pidController = new PIDController(1, 0, 0);
		pidController.enableContinuousInput(-1, 1); // this needs to change later TODO
	}

	@Override
	public void periodic() {
		angleMotor.set(pidController.calculate(angleEncoder.getDistance())); // spins angle motor
	}

	public void drive(double speed, double angle) {
		speedMotor.set(speed);

		double setpoint = angle * (MAX_VOLTS * 0.5) + (MAX_VOLTS * 0.5);
		if (setpoint < 0) {
			setpoint = MAX_VOLTS + setpoint;
		}
		if (setpoint > MAX_VOLTS) {
			setpoint = setpoint - MAX_VOLTS;
		}

		pidController.setSetpoint(setpoint); // point to spin angle motor
	}

    public void set(SwerveModule backRight, SwerveModule backLeft, SwerveModule frontRight,
            SwerveModule frontLeft) {
    }

}
