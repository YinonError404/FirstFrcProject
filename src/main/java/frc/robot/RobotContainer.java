package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.tank.Tank;
import frc.robot.subsystems.tank.TankCommands;

public class RobotContainer {
    private final CommandXboxController driverController =
            new CommandXboxController(0);
    private final double MAX_DRIVE_SPEED = 0.67;
    private final double MAX_ROTATION_SPEED = 0.42;
    private final Tank tank = Tank.getTank();

    public RobotContainer() {
        configureBindings();
    }

    private void configureBindings() {
        bindDefaultCommands();
        bindControllerCommands();
    }

    private void bindDefaultCommands() {
        tank.setDefaultCommand(
                TankCommands.getSetArcadeDriveCommand(
                        () -> modifyStick(-driverController.getLeftY()) * MAX_DRIVE_SPEED,
                        () -> modifyStick(driverController.getRightX()) * MAX_ROTATION_SPEED
                )
        );
    }

    private void bindControllerCommands() {
    }

    public Command getAutonomousCommand() {
        return Commands.print("No autonomous command configured");
    }

    private double modifyStick(double stickValue) {
        return Math.copySign(stickValue * stickValue, stickValue);
    }
}