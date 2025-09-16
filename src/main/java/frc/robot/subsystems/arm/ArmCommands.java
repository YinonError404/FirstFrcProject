package frc.robot.subsystems.arm;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import frc.robot.subsystems.Wheel.CallWheel;

public class ArmCommands {
    public static Command getCollectCommands() {
        return new StartEndCommand(
                () -> CallWheel.wheel.collect(),
                () -> CallWheel.wheel.stop(),
                CallWheel.wheel
        );
    }

    public static Command getCollectThenEjectCommand() {
        return new SequentialCommandGroup(
                getCollectCommands().withTimeout(3),
                getEjectCommands().withTimeout(3),
                new InstantCommand(
                        () -> CallWheel.wheel.stop(),
                        CallWheel.wheel

                )

        );
    }

    public static Command getEjectCommands() {
        return new StartEndCommand(
                () -> CallWheel.wheel.eject(),
                () -> CallWheel.wheel.stop(),
                CallWheel.wheel
        );
    }

    public static Command getTargetAngle() {
        return new InstantCommand(
                () -> CallArm.arm.setTargetAngle(),
                CallArm.arm
        );
    }
}
