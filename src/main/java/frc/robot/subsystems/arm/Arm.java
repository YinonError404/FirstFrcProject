package frc.robot.subsystems.arm;

import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.geometry.Rotation2d;

public class Arm extends SubsystemBase {
    private final TalonFX motor = ArmConstants.MOTOR;
    private final PIDController pidController = ArmConstants.PID_CONTROLLER;
    private final VoltageOut voltageRequest = new VoltageOut(0).withEnableFOC(ArmConstants.FOC_ENABLED);

    public Arm() {
    }

    public void setTargetState(ArmConstants.ArmState targetState) {
        setTargetAngle((targetState.armPositionDegrees));
    }

    void setTargetAngle(Rotation2d targetAngle) {
        setTargetVoltage(calculatePIDOutput(targetAngle));
    }

    private double calculatePIDOutput(Rotation2d targetAngle) {
        return pidController.calculate(getCurrentPositionRotations(), targetAngle.getRotations());
    }

    private double getCurrentPositionRotations() {
        return ArmConstants.ANGLE_STATUS_SIGNAL.refresh().getValueAsDouble();
    }

    public Rotation2d getCurrentAngle() {
        double rotations = getCurrentPositionRotations();
        return Rotation2d.fromRotations(rotations);
    }

    private void setTargetVoltage(double voltage) {
        motor.setControl(voltageRequest.withOutput(voltage));
    }

    void stop() {
        motor.stopMotor();
    }
}