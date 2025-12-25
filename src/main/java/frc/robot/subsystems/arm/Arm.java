package frc.robot.subsystems.arm;

import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.geometry.Rotation2d;

public class Arm extends SubsystemBase {
    private final TalonFX motor = ArmConstants.MOTOR;
    private final PIDController pidController = ArmConstants.PID_CONTROLLER;
    private final VoltageOut voltageRequest = new VoltageOut(0).withEnableFOC(ArmConstants.FOC_ENABLED);

    private TrapezoidProfile angleMotorProfile = null;
    private double lastAngleMotorProfileGenerationTime;

    public Arm() {
    }

    void setTargetState(ArmConstants.ArmState targetState) {
        setTargetAngle(targetState.targetAngle);
    }

    void setTargetAngle(Rotation2d targetAngle) {
        setTargetVoltage(calculatePIDOutput(targetAngle));
    }

    void stop() {
        motor.stopMotor();
    }

    void setTargetAngleWithProfile(Rotation2d targetAngle) {
        TrapezoidProfile.State currentState = new TrapezoidProfile.State(getCurrentAngle().getRotations(), 0);
    }

    private double calculatePIDOutput(Rotation2d targetAngle) {
        return pidController.calculate(getCurrentAngle().getRotations(), targetAngle.getRotations());
    }

    private Rotation2d getCurrentAngle() {
        double rotations = ArmConstants.ANGLE_STATUS_SIGNAL.refresh().getValueAsDouble();
        return Rotation2d.fromRotations(rotations);
    }

    private double getAngleMotorProfileTime() {
        return Timer.getFPGATimestamp() - lastAngleMotorProfileGenerationTime;
    }

    private void setTargetVoltage(double voltage) {
        motor.setControl(voltageRequest.withOutput(voltage));
    }

    void generateProfile(Rotation2d targetAngle) {
        angleMotorProfile = new TrapezoidProfile(
                ArmConstants.CONSTRAINTS,
                new TrapezoidProfile.State(targetAngle.getDegrees(), 0),
                new TrapezoidProfile.State(getCurrentAngle().getDegrees(), getAngleMotorVelocityRotationsPerSecond())
        );

        lastAngleMotorProfileGenerationTime = Timer.getFPGATimestamp();
    }
}


}