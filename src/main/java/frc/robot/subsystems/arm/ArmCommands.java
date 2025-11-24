package frc.robot.subsystems.arm;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.RobotContainer;

public class ArmCommands {
    public static Command getSetTargetAngleCommand(double targetAngle) {
        return new FunctionalCommand(
                () -> RobotContainer.ARM.setTargetAngle(targetAngle),
                () -> RobotContainer.ARM.setTargetAngle(targetAngle),
                (Interrupted) -> RobotContainer.ARM.stop(),
                () -> false,
                RobotContainer.ARM
        );
    }
}