package frc.robot.subsystems.arm;

import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.CANcoder;
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
    private final CANcoder encoder = ArmConstants.ENCODER;
    private TrapezoidProfile profile = new TrapezoidProfile(ArmConstants.CONSTRAINTS);
    private TrapezoidProfile.State goalState = new TrapezoidProfile.State();
    private TrapezoidProfile.State setpointState = new TrapezoidProfile.State();
    private TrapezoidProfile.State initialState = new TrapezoidProfile.State();
    private final Timer profileTimer = new Timer();

    public Arm() {
        profileTimer.reset();
        profileTimer.start();
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

    void startMotionProfile(double targetPosition) {
        initialState = new TrapezoidProfile.State(
                getCurrentAngle().getRotations(),
                0
        );

        goalState = new TrapezoidProfile.State(targetPosition, 0);

        profileTimer.reset();
    }

    public void updateMotionProfile() {
        setpointState = profile.calculate(
                profileTimer.get(),
                initialState,
                goalState
        );

        double output = pidController.calculate(
                getCurrentAngle().getRotations(),
                setpointState.position
        );

        setTargetVoltage(output);
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
