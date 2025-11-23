package frc.robot.subsystems.arm;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.RobotContainer;

public class ArmCommands {
    public static Command getSetTargetAngleCommand(double targetAngle) {
        return new FunctionalCommand(
                () -> RobotContainer.ARM.getSetTargetAngle(targetAngle),
                () ->
                (Interrupted) ->
                () ->
                RobotContainer.ARM
        );
    }