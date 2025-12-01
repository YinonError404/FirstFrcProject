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

    public static Command getSetStateCommand(ArmConstants.ArmState armState) {
        return new FunctionalCommand(
                () -> RobotContainer.ARM.setTargetState(armState),
                () -> RobotContainer.ARM.setTargetState(armState),
                (Interrupted) -> RobotContainer.ARM.stop(),
                () -> false,
                RobotContainer.ARM
        );
    }
}