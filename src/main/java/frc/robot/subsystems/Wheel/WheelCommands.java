package frc.robot.subsystems.Wheel;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import frc.robot.RobotContainer;

public class WheelCommands {
    public static Command getCollectCommands() {
        return new StartEndCommand(
                () -> RobotContainer.WHEEL.collect(),
                () -> RobotContainer.WHEEL.stop(),
                RobotContainer.WHEEL
        );
    }

    public static Command getEjectCommands() {
        return new StartEndCommand(
                () -> RobotContainer.WHEEL.eject(),
                () -> RobotContainer.WHEEL.stop(),
                RobotContainer.WHEEL
        );
    }

    public static Command getCollectThenEjectCommand() {
        return new SequentialCommandGroup(
                getCollectCommands().withTimeout(3),
                getEjectCommands().withTimeout(3),
                new InstantCommand(
                        () -> RobotContainer.WHEEL.stop(),
                        RobotContainer.WHEEL

                )

        );
    }

    public static Command getStopCommand() {
        return new StartEndCommand(
                () -> RobotContainer.WHEEL.stop(),
                () -> {},
                RobotContainer.WHEEL
        );
    }
    
    public static Command getStop() {
        return new StartEndCommand(
                () -> RobotContainer.WHEEL.stop(),
                () -> {},
                RobotContainer.WHEEL

        );
    }

}
