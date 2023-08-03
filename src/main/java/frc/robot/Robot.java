package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.motorcontrol.Spark;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxAbsoluteEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Subsystems.*;

public class Robot extends TimedRobot {
	
private final XboxController m_DriveController = new XboxController(0);
private final XboxController m_xboxcontroller = new XboxController(1);
    private CommandBase m_auto;
    private final SendableChooser<CommandBase> m_autoChooser = new SendableChooser<CommandBase>();
	
	private Spark m_leftFront = new Spark(2);
	private Spark m_leftBack = new Spark(3);
	private Spark m_RightFront = new Spark(4);
	private Spark m_RightBack = new Spark(5);

	private CANSparkMax m_ALeftFront = new CANSparkMax(1, MotorType.kBrushless);
	private CANSparkMax m_ALeftBack = new CANSparkMax(2, MotorType.kBrushless);
	private CANSparkMax m_ARightFront = new CANSparkMax(3, MotorType.kBrushless);
	private CANSparkMax m_ARightBack = new CANSparkMax(4, MotorType.kBrushless);
// all this is untested

    private SwerveModule backRight = new SwerveModule(m_ALeftBack, m_leftBack, 0);//0); //angleMotor Port, speedMotor port, ecoder 1, and 2
    private SwerveModule backLeft = new SwerveModule(m_ALeftFront, m_leftFront, 1);//1);
    private SwerveModule frontRight = new SwerveModule(m_ARightBack, m_RightBack, 3);
    private SwerveModule frontLeft = new SwerveModule(m_ARightFront, m_RightFront, 2);
 
	private SwerveDrive swerveDrive = new SwerveDrive(backRight, backLeft, frontRight, frontLeft);
	
	

	public Robot() {
		// m_autoChooser.setDefaultOption("No_Auto", new NoAutonomous());
		
		

	}

	@Override
	public void robotInit() {
		
		SmartDashboard.putData("Auto_Choice", m_autoChooser);
	
	}

	@Override
	public void robotPeriodic() {
		CommandScheduler.getInstance().run();
		
        double x1 = m_DriveController.getLeftX();
        double y1 = m_DriveController.getLeftY();
        double x2 = -m_DriveController.getRightX();

		swerveDrive.drive(x1, y1, x2);

	}

	@Override
	public void disabledInit() {
	}

	@Override
	public void disabledPeriodic() {
	}

	@Override
	public void autonomousInit() {
		m_auto = m_autoChooser.getSelected();

		if (m_auto != null) {
			m_auto.schedule();
		}
	}

	/** This function is called periodically during autonomous. */
	@Override
	public void autonomousPeriodic() {
	}

	@Override
	public void teleopInit() {
	}

	/** This function is called periodically during operator control. */
	@Override
	public void teleopPeriodic() {
		
		
		
	}

	@Override
	public void testInit() {
		// Cancels all running commands at the start of test mode.
		CommandScheduler.getInstance().cancelAll();
	}

	/** This function is called periodically during test mode. */
	@Override
	public void testPeriodic() {
	}

	/** This function is called once when the robot is first started up. */
	@Override
	public void simulationInit() {
	}

	/** This function is called periodically whilst in simulation. */
	@Override
	public void simulationPeriodic() {
	}
}
