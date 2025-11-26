package frc.robot.subsystems.arm;

import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.units.PerUnit;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {
    private final TalonFX motor = ArmConstants.MOTOR;
    private final PIDController pidController = ArmConstants.PID_CONTROLLER;
    private final VoltageOut voltageRequest = new VoltageOut(0).withEnableFOC(ArmConstants.FOC_ENABLED);

    public Arm() {
    }

    void setTargetAngle(double targetAngle) {
        setTargetVoltage(calculatePIDOutput(targetAngle));
    }

    double calculatePIDOutput(double targetAngle) {
        return pidController.calculate(getCurrentPositionRotations(), targetAngle);
    }

    double getCurrentPositionRotations() {
        return ArmConstants.ANGLE_STATUS_SIGNAL.refresh().getValueAsDouble();
    }

    void setTargetVoltage(double voltage) {
        motor.setControl(voltageRequest.withOutput(voltage));
    }

    void stop() {
        motor.stopMotor();
    }

}