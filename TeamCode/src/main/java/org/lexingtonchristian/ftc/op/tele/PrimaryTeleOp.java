package org.lexingtonchristian.ftc.op.tele;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.lexingtonchristian.ftc.components.Drivetrain;
import org.lexingtonchristian.ftc.components.Intake;

@TeleOp(name = "Primary", group = "Competition")
public class PrimaryTeleOp extends OpMode {

    private Drivetrain drivetrain;
    private Intake intake;

    private final Gamepad currentGamepad = new Gamepad();
    private final Gamepad previousGamepad = new Gamepad();

    @Override
    public void init() {

        drivetrain = new Drivetrain(hardwareMap);
        intake = new Intake(hardwareMap);

        currentGamepad.copy(gamepad1);

    }

    @Override
    public void loop() {

        previousGamepad.copy(currentGamepad);
        currentGamepad.copy(gamepad1);

        if (currentGamepad.options && !previousGamepad.options) drivetrain.resetHeading();
        if (currentGamepad.back && !previousGamepad.back) drivetrain.toggleFieldCentric();

        double x = currentGamepad.left_stick_x * -1; // left/right
        double y = currentGamepad.left_stick_y * -1; // forward/backward
        double r = currentGamepad.right_stick_x;     // rotate

        boolean runIntake = currentGamepad.right_trigger_pressed;

        drivetrain.drive(x, y, r);

        intake.setActive(runIntake);
        intake.tick();

    }

}
