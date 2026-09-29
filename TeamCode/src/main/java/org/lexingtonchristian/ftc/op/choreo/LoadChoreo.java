package org.lexingtonchristian.ftc.op.choreo;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.lexingtonchristian.ftc.choreo.SaveReader;
import org.lexingtonchristian.ftc.choreo.Snapshot;
import org.lexingtonchristian.ftc.components.Drivetrain;
import org.lexingtonchristian.ftc.components.Intake;
import org.lexingtonchristian.ftc.util.Constants;

import java.io.IOException;

public class LoadChoreo extends OpMode {

    private final SaveReader reader;

    private Drivetrain drivetrain;
    private Intake intake;

    private long current;
    private long previous;

    public LoadChoreo(String name) {
        try {
            reader = new SaveReader(name + ".choreo");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void init() {
        drivetrain = new Drivetrain(hardwareMap);
        intake = new Intake(hardwareMap);
    }

    @Override
    public void start() {
        previous = System.currentTimeMillis();
    }

    @Override
    public void loop() {

        current = System.currentTimeMillis();
        if (current < previous + Constants.CHOREO_INTERVAL) return;
        previous = current;

        try {
            Snapshot snapshot = reader.readSnapshot();
            if (snapshot == null) {
                requestOpModeStop();
                return;
            }
            drivetrain.readValues(snapshot);
            intake.readValues(snapshot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void stop() {
        reader.close();
    }

}
