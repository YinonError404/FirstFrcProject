package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.tank.Tank;
import frc.robot.subsystems.tank.TankCommands;

public class RobotContainer {
    private final CommandXboxController driverController =
            new CommandXboxController(0);
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
                        () -> -driverController.getRightY(),
                        () -> -driverController.getLeftX()
                )
        );
    }

    private void bindControllerCommands() {
    }

    public Command getAutonomousCommand() {
        return Commands.print("No autonomous command configured");
    }

    private double calculateDriveSpeed(double stickMovement) {
        final double squaredShiftModeValue = driverController.getRightTriggerAxis();
        final double minimumShiftValueCoefficient = -1;
        final double speed = 1 - squaredShiftModeValue * minimumShiftValueCoefficient;
        return stickMovement / speed;
    }
}