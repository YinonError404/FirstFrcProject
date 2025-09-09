package frc.robot.subsystems.arm;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.units.measure.Angle;

public class ArmConstants {
    private static final int MOTOR_ID = 1;

    static final TalonFX MOTOR = new TalonFX(MOTOR_ID);

    private static final InvertedValue INVERTED_VALUE = InvertedValue.CounterClockwise_Positive;

    private static final NeutralModeValue NEUTRAL_MODE = NeutralModeValue.Coast;

    private static final double GEAR_RATIO = 1.5;


    static {
        TalonFXConfiguration config = new TalonFXConfiguration();
        config.Feedback.SensorToMechanismRatio = GEAR_RATIO;
        config.MotorOutput.Inverted = INVERTED_VALUE;
        config.MotorOutput.NeutralMode = NEUTRAL_MODE;
        MOTOR.getConfigurator().apply(config);
    }

    private static final int CanCoder_ID = 1;
    static final CANcoder canCoder = new CANcoder(CanCoder_ID, "rio");



    static{
        CANcoderConfiguration configuration = new CANcoderConfiguration();

        canCoder.getConfigurator().apply(configuration);
    }


}
