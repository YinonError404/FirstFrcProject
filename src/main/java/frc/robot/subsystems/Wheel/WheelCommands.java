package frc.robot.subsystems.Wheel;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.StartEndCommand;

import java.util.concurrent.ConcurrentMap;

public class WheelCommands {
    public static Command getCollectCommands() {
        return new StartEndCommand(
                () -> CallWheel.wheel.collect(),
                () -> CallWheel.wheel.stop(),
                CallWheel.wheel
        );
    }

    public static Command getEjectCommands() {
        return new StartEndCommand(
                () -> CallWheel.wheel.eject(),
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

    public static Command getStop() {
        return new InstantCommand(
                () -> CallWheel.wheel.stop(),
                CallWheel.wheel
        );
    }

}


