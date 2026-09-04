package org.lexingtonchristian.ftc.choreo;

import org.firstinspires.ftc.robotcore.internal.system.AppUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class SaveWriter {

    protected File file;
    protected BufferedWriter writer;

    public SaveWriter(String filename) throws IOException {

        file = new File(filename);
        if (!file.isAbsolute()) file = new File(AppUtil.ROBOT_DATA_DIR, filename);

        File directory = file.getParentFile();
        AppUtil.getInstance().ensureDirectoryExists(directory);

        writer = new BufferedWriter(new FileWriter(file));

    }

    public void writeSnapshot(Snapshot snapshot) throws IOException {

        writer.append(String.format("%1$s/", snapshot.time));

        for (Map.Entry<String, Double> entry : snapshot.values.entrySet()) {
            writer.append(String.format("%1$s:%2$s,", entry.getKey(), entry.getValue()));
        }

        writer.append("\r\n");

    }

    public void close() {
        try {
            writer.close();
        } catch (IOException ignored) {}
    }

}
