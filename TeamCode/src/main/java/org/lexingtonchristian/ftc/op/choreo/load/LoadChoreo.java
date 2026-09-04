package org.lexingtonchristian.ftc.op.choreo.load;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.lexingtonchristian.ftc.choreo.SaveReader;
import org.lexingtonchristian.ftc.choreo.Snapshot;
import org.lexingtonchristian.ftc.components.Drivetrain;
import org.lexingtonchristian.ftc.components.Intake;

import java.io.IOException;

public abstract class LoadChoreo extends OpMode {

    private Drivetrain drivetrain;
    private Intake intake;

    private final Gamepad currentGamepad = new Gamepad();
    private final Gamepad previousGamepad = new Gamepad();

    private SaveReader reader;

    @Override
    public void init() {

        drivetrain = new Drivetrain(hardwareMap);
        intake = new Intake(hardwareMap);

        try {
            reader = new SaveReader(getName());
        } catch (IOException ignored) {}

    }

    @Override
    public void loop() {

        try {

            Snapshot snapshot = reader.readSnapshot();
            while (snapshot != null) {
                drivetrain.readValues(snapshot);
                intake.readValues(snapshot);
                snapshot = reader.readSnapshot();
            }

        } catch (IOException ignored) {}

    }

    @Override
    public void stop() {
        reader.close();
    }

    public abstract String getName();

}
