package org.lexingtonchristian.ftc.op.choreo.save;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.lexingtonchristian.ftc.choreo.SaveReader;
import org.lexingtonchristian.ftc.choreo.SaveWriter;
import org.lexingtonchristian.ftc.choreo.Snapshot;
import org.lexingtonchristian.ftc.components.Drivetrain;
import org.lexingtonchristian.ftc.components.Intake;

import java.io.IOException;
import java.util.List;

public abstract class SaveChoreo extends OpMode {

    private Drivetrain drivetrain;
    private Intake intake;

    private Gamepad currentGamepad = new Gamepad();
    private Gamepad previousGamepad = new Gamepad();

    private SaveWriter writer;

    @Override
    public void init() {

        drivetrain = new Drivetrain(hardwareMap);
        intake = new Intake(hardwareMap);

        try {
            writer = new SaveWriter(getName());
        } catch (IOException ignored) {}

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

        boolean runIntake = currentGamepad.right_trigger_pressed;

        drivetrain.drive(x, y, r);

        intake.setActive(runIntake);
        intake.tick();

        try {

            Snapshot snapshot = new Snapshot(System.currentTimeMillis());
            drivetrain.writeValues(snapshot);
            intake.writeValues(snapshot);
            writer.writeSnapshot(snapshot);

        } catch (IOException ignored) {}

    }

    @Override
    public void stop() {
        writer.close();
    }

    public abstract String getName();

}
