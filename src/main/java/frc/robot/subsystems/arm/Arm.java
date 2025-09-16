package frc.robot.subsystems.arm;

import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {
    private final TalonFX motor = ArmConstants.MOTOR;
    private final CANcoder canCoder = ArmConstants.canCoder;
    private final PIDController pidController = ArmConstants.pid;



    public void setTargetAngle(){
        motor.set(pidController.calculate(canCoder.getPosition().getValueAsDouble(), 90));
    }

}