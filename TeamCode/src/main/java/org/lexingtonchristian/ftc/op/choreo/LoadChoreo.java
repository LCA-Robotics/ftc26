package org.lexingtonchristian.ftc.op.choreo;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.lexingtonchristian.ftc.choreo.SaveReader;
import org.lexingtonchristian.ftc.choreo.Snapshot;
import org.lexingtonchristian.ftc.components.Drivetrain;
import org.lexingtonchristian.ftc.components.Intake;

import java.io.IOException;

public class LoadChoreo extends OpMode {

    private final String name;

    private Drivetrain drivetrain;
    private Intake intake;

    private SaveReader reader;

    public LoadChoreo(String name) {
        this.name = name;
    }

    @Override
    public void init() {

        drivetrain = new Drivetrain(hardwareMap);
        intake = new Intake(hardwareMap);

        try {
            reader = new SaveReader(name + ".choreo");
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

}
