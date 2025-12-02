package frc.robot.subsystems.tank;

import edu.wpi.first.wpilibj2.command.SubsystemBase;;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Tank extends SubsystemBase {
    private static final Tank tank = new Tank();
    private final DifferentialDrive differentialDrive = TankConstants.DIFFERENTIAL_DRIVE;

    public static Tank getTank() {
        return tank;
    }

    void setArcadeDrive(double driveSpeed, double rotationSpeed) {
        differentialDrive.arcadeDrive(driveSpeed, rotationSpeed);
    }

    void stop() {
        differentialDrive.stopMotor();
    }
}
