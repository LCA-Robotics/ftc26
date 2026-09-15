package org.lexingtonchristian.ftc.choreo;

import org.lexingtonchristian.ftc.components.ChoreoDevice;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Choreographer {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private SaveWriter writer;
    private SaveReader reader;

    public Choreographer(String name) {

        String filename = name + ".choreo";

        try {
            writer = new SaveWriter(filename);
            reader = new SaveReader(filename);
        } catch (IOException ignored) {}

    }

    public void initWriteTask(ChoreoDevice... devices) {
        scheduler.scheduleWithFixedDelay(() -> {

            try {
                Snapshot snapshot = new Snapshot(System.currentTimeMillis());
                for (ChoreoDevice device : devices) {
                    device.writeValues(snapshot);
                }
                writer.writeSnapshot(snapshot);
            } catch (IOException ignored) {}

        }, 0, 50, TimeUnit.MILLISECONDS);
    }

    public void initReadTask(ChoreoDevice... devices) {
        scheduler.scheduleWithFixedDelay(() -> {

            try {
                Snapshot snapshot = reader.readSnapshot();
                while (snapshot != null) {
                    for (ChoreoDevice device : devices) {
                        device.readValues(snapshot);
                    }
                    snapshot = reader.readSnapshot();
                }
            } catch (IOException ignored) {}

        }, 0, 50, TimeUnit.MILLISECONDS);
    }

    public void close() {
        writer.close();
        reader.close();
    }

}
