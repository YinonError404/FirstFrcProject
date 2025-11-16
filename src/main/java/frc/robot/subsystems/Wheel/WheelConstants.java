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

    static final double COLLECT = 1.0;
    static final double EJECT = -1.0;
    static final double STOP = 0.0;
    static final StatusSignal<Angle> ANGLE = MOTOR.getPosition();

    static {
        TalonFXConfiguration config = new TalonFXConfiguration();
        config.Feedback.SensorToMechanismRatio = 1.5;
        config.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
        config.MotorOutput.NeutralMode = NeutralModeValue.Coast;
        MOTOR.getConfigurator().apply(config);
        ANGLE.setUpdateFrequency(100);
    }
}
