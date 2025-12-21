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
    private TrapezoidProfile profile = new TrapezoidProfile(ArmConstants.ANGLE_CONSTRAINTS);
    private double lastAngleMotorProfileGenerationTime;

    public Arm() {
    }

    private void generateAngleMotorProfile(Rotation2d targetAngle) {
        profile = new TrapezoidProfile(ArmConstants.ANGLE_CONSTRAINTS);
        lastAngleMotorProfileGenerationTime = Timer.getFPGATimestamp();
    }

    private double getProfileTime() {
        return Timer.getFPGATimestamp() - lastAngleMotorProfileGenerationTime;
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

    private double calculatePIDOutput(Rotation2d targetAngle) {
        return pidController.calculate(getCurrentAngle().getRotations(), targetAngle.getRotations());
    }

    private Rotation2d getCurrentAngle() {
        double rotations = ArmConstants.ANGLE_STATUS_SIGNAL.refresh().getValueAsDouble();
        return Rotation2d.fromRotations(rotations);
    }

    private void setTargetVoltage(double voltage) {
        motor.setControl(voltageRequest.withOutput(voltage));
    }
}