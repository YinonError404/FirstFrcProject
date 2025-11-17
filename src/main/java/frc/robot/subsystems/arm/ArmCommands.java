package frc.robot.subsystems.arm;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.subsystems.Wheel.CallWheel;

public class ArmCommands {
    public static Command getSetTargetAngleCommands() {
        return new FunctionalCommand(
                () -> CallArm.arm.setTargetAngle(),
                () -> CallArm.arm.setTargetAngle(),
                (Interrupted) ->
                () ->
        );
    }