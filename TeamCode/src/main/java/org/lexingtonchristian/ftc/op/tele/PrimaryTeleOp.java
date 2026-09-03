package org.lexingtonchristian.ftc.op.tele;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.lexingtonchristian.ftc.components.Drivetrain;

@TeleOp(name = "Primary", group = "Competition")
public class PrimaryTeleOp extends OpMode {

    private Drivetrain drivetrain;

    private final Gamepad currentGamepad = new Gamepad();
    private final Gamepad previousGamepad = new Gamepad();

    @Override
    public void init() {

        drivetrain = new Drivetrain(hardwareMap);

        currentGamepad.copy(gamepad1);

    }

    @Override
    public void loop() {

        previousGamepad.copy(currentGamepad);
        currentGamepad.copy(gamepad1);

        if (currentGamepad.options && !previousGamepad.options) drivetrain.resetHeading();
        if (currentGamepad.back && !previousGamepad.back) drivetrain.toggleFieldCentric();

        double x = currentGamepad.left_stick_x;
        double y = currentGamepad.left_stick_y * -1;
        double r = currentGamepad.right_stick_x;

        drivetrain.drive(x, y, r);

    }

}
