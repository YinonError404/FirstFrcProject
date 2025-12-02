package frc.robot.subsystems.tank;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public record TankConstants() {
    private static final int LEFT_MOTOR_ID = 1, RIGHT_MOTOR_ID = 2;
    static final WPI_TalonSRX LEFT_MOTOR = new WPI_TalonSRX(LEFT_MOTOR_ID);
    static final WPI_TalonSRX RIGHT_MOTOR = new WPI_TalonSRX(RIGHT_MOTOR_ID);

    static final DifferentialDrive DIFFERENTIAL_DRIVE = new DifferentialDrive(LEFT_MOTOR, RIGHT_MOTOR);
    private static final double VOLTAGE_LIMIT = 12;

    private static final boolean LEFT_MOTOR_INVERTED_VALUE = true;
    private static final boolean RIGHT_MOTOR_INVERTED_VALUE = false;
    private static final NeutralMode NATURAL_MODE = NeutralMode.Brake;

    static {
        configLeftMotors();
        configRightMotors();
    }


    private static void configLeftMotors() {
        LEFT_MOTOR.configFactoryDefault();
        LEFT_MOTOR.enableVoltageCompensation(true);
        LEFT_MOTOR.configVoltageCompSaturation(VOLTAGE_LIMIT);
    }

    private static void configRightMotors() {
        RIGHT_MOTOR.configFactoryDefault();
        RIGHT_MOTOR.enableVoltageCompensation(true);
        RIGHT_MOTOR.configVoltageCompSaturation(VOLTAGE_LIMIT);
    }
}
