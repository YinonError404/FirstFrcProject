package frc.robot.subsystems.Wheel;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.units.measure.Angle;

import java.util.PrimitiveIterator;

public class WheelConstants {
    private static final int MOTOR_ID = 1;

    static final TalonFX MOTOR = new TalonFX(MOTOR_ID);

    private static final InvertedValue INVERTED_VALUE = InvertedValue.CounterClockwise_Positive;

    private static final NeutralModeValue NEUTRAL_MODE = NeutralModeValue.Coast;

    private static final double GEAR_RATIO = 1.5;

    static final double COLLECT = 1.0;
    static final double EJECT = -1.0;
    static final double STOP = 0.0;
    static final StatusSignal<Angle> angle = MOTOR.getPosition();

    static {
        TalonFXConfiguration config = new TalonFXConfiguration();
        config.Feedback.SensorToMechanismRatio = GEAR_RATIO;
        config.MotorOutput.Inverted = INVERTED_VALUE;
        config.MotorOutput.NeutralMode = NEUTRAL_MODE;
        MOTOR.getConfigurator().apply(config);
        angle.setUpdateFrequency(100);
    }

    
}
