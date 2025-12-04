package frc.robot.subsystems.tank;

import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class TankConstants {
    private static final int LEFT_MOTOR_1_ID = 1, LEFT_MOTOR_2_ID = 2 ,RIGHT_MOTOR_1_ID = 3, RIGHT_MOTOR_2_ID = 4;
    static final WPI_TalonSRX LEFT_MOTOR_1 = new WPI_TalonSRX(LEFT_MOTOR_1_ID);
    static final WPI_TalonSRX LEFT_MOTOR_2 = new WPI_TalonSRX(LEFT_MOTOR_1_ID);
    static final WPI_TalonSRX RIGHT_MOTOR_1= new WPI_TalonSRX(RIGHT_MOTOR_1_ID);
    static final WPI_TalonSRX RIGHT_MOTOR_2 = new WPI_TalonSRX(RIGHT_MOTOR_1_ID);

    static final DifferentialDrive DIFFERENTIAL_DRIVE = new DifferentialDrive(LEFT_MOTOR_1, LEFT_MOTOR_2);
    private static final double VOLTAGE_LIMIT = 12;

    private static final boolean LEFT_MOTOR_1_INVERTED_VALUE = true;
    private static final boolean LEFT_MOTOR_2_INVERTED_VALUE = true;
    private static final boolean RIGHT_MOTOR_1_INVERTED_VALUE = false;
    private static final boolean RIGHT_MOTOR_2_INVERTED_VALUE = false;
    private static final NeutralMode NATURAL_MODE = NeutralMode.Brake;

    static {
        configLeftMotors();
        configRightMotors();
    }


    private static void configLeftMotors() {
        LEFT_MOTOR_1.configFactoryDefault();
        LEFT_MOTOR_2.configFactoryDefault();
        LEFT_MOTOR_1.enableVoltageCompensation(true);
        LEFT_MOTOR_2.enableVoltageCompensation(true);
        LEFT_MOTOR_1.configVoltageCompSaturation(VOLTAGE_LIMIT);
        LEFT_MOTOR_2.configVoltageCompSaturation(VOLTAGE_LIMIT);
        LEFT_MOTOR_1.setInverted(LEFT_MOTOR_1_INVERTED_VALUE);
        LEFT_MOTOR_2.setInverted(LEFT_MOTOR_2_INVERTED_VALUE);
        LEFT_MOTOR_2.follow(LEFT_MOTOR_1, FollowerType.PercentOutput);

    }

    private static void configRightMotors() {
        RIGHT_MOTOR_1.configFactoryDefault();
        RIGHT_MOTOR_2.configFactoryDefault();
        RIGHT_MOTOR_1.enableVoltageCompensation(true);
        RIGHT_MOTOR_2.enableVoltageCompensation(true);
        RIGHT_MOTOR_1.configVoltageCompSaturation(VOLTAGE_LIMIT);
        RIGHT_MOTOR_2.configVoltageCompSaturation(VOLTAGE_LIMIT);
        RIGHT_MOTOR_1.setInverted(RIGHT_MOTOR_1_INVERTED_VALUE);
        RIGHT_MOTOR_2.setInverted(RIGHT_MOTOR_2_INVERTED_VALUE);
        RIGHT_MOTOR_2.follow(RIGHT_MOTOR_1,FollowerType.PercentOutput);
    }
}
