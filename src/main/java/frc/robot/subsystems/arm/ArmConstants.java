package frc.robot.subsystems.arm;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.units.measure.Angle;

public class ArmConstants {
    private static final int MOTOR_ID = 1;
    static final TalonFX MOTOR = new TalonFX(MOTOR_ID);

    private static final InvertedValue INVERTED_VALUE = InvertedValue.CounterClockwise_Positive;
    private static final NeutralModeValue NEUTRAL_MODE = NeutralModeValue.Coast;
    private static final double GEAR_RATIO = 1.5;
    public static final PIDController pid = new PIDController(0, 0, 0);

    private static final int CANCODER_ID = 1;
    static final CANcoder CANCODER = new CANcoder(CANCODER_ID);

    private static void configMotor(){
        TalonFXConfiguration config = new TalonFXConfiguration();
        config.Feedback.SensorToMechanismRatio = GEAR_RATIO;
        config.MotorOutput.Inverted = INVERTED_VALUE;
        config.MotorOutput.NeutralMode = NEUTRAL_MODE;
        MOTOR.getConfigurator().apply(config);
    }

    private static void configEncoder(){
        CANcoderConfiguration config = new CANcoderConfiguration();
        CANCODER.getConfigurator().apply(config);
    }

    static {
        configMotor();
    }

    static {
        configEncoder();
    }

}
