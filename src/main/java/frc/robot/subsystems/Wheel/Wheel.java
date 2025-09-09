package frc.robot.subsystems.Wheel;


import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static edu.wpi.first.units.Units.Degrees;

public class Wheel extends SubsystemBase {
    private final TalonFX motor = WheelConstants.MOTOR;

    public double getAnglePosition() {
        Angle converAngle = WheelConstants.angle.refresh().getValue();
        return converAngle.in(Degrees);
    }

    public void collect() {
        motor.setVoltage(WheelConstants.COLLECT);
    }

    public void eject() {
        motor.setVoltage(WheelConstants.EJECT);
        ;
    }

    public void stop() {
        motor.setVoltage(WheelConstants.STOP);
        ;
    }

    public Wheel() {
    }

    
}

