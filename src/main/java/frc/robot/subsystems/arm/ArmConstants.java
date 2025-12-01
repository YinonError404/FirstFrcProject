package frc.robot.subsystems.arm;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.configs.MagnetSensorConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.signals.SensorDirectionValue;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.units.measure.Angle;

public class ArmConstants {
    private static final int ENCODER_ID = 1;
    private static final int MOTOR_ID = 1;
    static final CANcoder ENCODER = new CANcoder(ENCODER_ID);
    static final TalonFX MOTOR = new TalonFX(MOTOR_ID);

    static final PIDController PID_CONTROLLER = new PIDController(0, 0, 0);
    static final boolean FOC_ENABLED = true;
    static final StatusSignal<Angle> ANGLE_STATUS_SIGNAL = ENCODER.getPosition();

    static {
        configMotor();
        configEncoder();
    }

    private static void configMotor() {
        TalonFXConfiguration config = new TalonFXConfiguration();
        config.Feedback.SensorToMechanismRatio = 1.5;
        config.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
        config.MotorOutput.NeutralMode = NeutralModeValue.Coast;
        MOTOR.getConfigurator().apply(config);
        MOTOR.optimizeBusUtilization();
    }

    private static void configEncoder() {
        CANcoderConfiguration config = new CANcoderConfiguration();
        config.MagnetSensor.SensorDirection = SensorDirectionValue.CounterClockwise_Positive;
        config.MagnetSensor.AbsoluteSensorDiscontinuityPoint = 0.5;
        config.MagnetSensor.MagnetOffset = 0;
        ENCODER.getConfigurator().apply(config);
        ANGLE_STATUS_SIGNAL.setUpdateFrequency(100);
        ENCODER.optimizeBusUtilization();
    }

    public enum ArmState {
        high(167),
        low(67),
        rest(0);

        public final double armPositionDegrees;

        ArmState(double armPositionDegrees) {
            this.armPositionDegrees = armPositionDegrees;
        }
    }
}
