package frc.robot.subsystems.arm;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.*;
import frc.robot.RobotContainer;

public class ArmCommands {
    public static Command getSetTargetAngleCommand(Rotation2d targetAngle) {
        return new FunctionalCommand(
                () -> RobotContainer.ARM.setTargetAngle(targetAngle),
                () -> RobotContainer.ARM.setTargetAngle(targetAngle),
                (Interrupted) -> RobotContainer.ARM.stop(),
                () -> false,
                RobotContainer.ARM
        );
    }

    public static Command getSetTargetStateCommand(ArmConstants.ArmState targetState) {
        return new FunctionalCommand(
                () -> RobotContainer.ARM.setTargetState(targetState),
                () -> RobotContainer.ARM.setTargetState(targetState),
                (Interrupted) -> RobotContainer.ARM.stop(),
                () -> false,
                RobotContainer.ARM
        );
    }
}