package org.lexingtonchristian.ftc.op.choreo;

import org.lexingtonchristian.ftc.choreo.SaveWriter;
import org.lexingtonchristian.ftc.choreo.Snapshot;
import org.lexingtonchristian.ftc.op.tele.PrimaryTeleOp;
import org.lexingtonchristian.ftc.util.Constants;

import java.io.IOException;

public class SaveChoreo extends PrimaryTeleOp {

    private final SaveWriter writer;

    private long current;
    private long previous;

    public SaveChoreo(String name) {
        try {
            writer = new SaveWriter(name + ".choreo");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void start() {
        previous = System.currentTimeMillis();
    }

    @Override
    public void loop() {

        super.loop();

        current = System.currentTimeMillis();
        if (current < previous + Constants.CHOREO_INTERVAL) return;
        previous = current;

        try {
            Snapshot snapshot = new Snapshot(current);
            drivetrain.writeValues(snapshot);
            intake.writeValues(snapshot);
            writer.writeSnapshot(snapshot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void stop() {
        writer.close();
    }

}
