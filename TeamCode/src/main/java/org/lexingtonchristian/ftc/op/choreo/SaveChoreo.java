package org.lexingtonchristian.ftc.op.choreo;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.lexingtonchristian.ftc.choreo.SaveWriter;
import org.lexingtonchristian.ftc.choreo.Snapshot;
import org.lexingtonchristian.ftc.op.tele.PrimaryTeleOp;

import java.io.IOException;

public class SaveChoreo extends PrimaryTeleOp {

    private final String name;

    private SaveWriter writer;

    public SaveChoreo(String name) {
        this.name = name;
    }

    @Override
    public void init() {

        super.init();

        try {
            writer = new SaveWriter(name + ".choreo");
        } catch (IOException ignored) {}

    }

    @Override
    public void loop() {

        super.loop();

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

}
