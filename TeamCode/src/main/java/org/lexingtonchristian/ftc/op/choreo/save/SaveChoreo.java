package org.lexingtonchristian.ftc.op.choreo.save;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.lexingtonchristian.ftc.components.Drivetrain;

public abstract class SaveChoreo extends OpMode {

    private Drivetrain drivetrain;

    private Gamepad currentGamepad = new Gamepad();
    private Gamepad previousGamepad = new Gamepad();

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
        double y = currentGamepad.left_stick_y;
        double r = currentGamepad.right_stick_x;

        drivetrain.drive(x, y, r);

    }

    public abstract String getName();

}
