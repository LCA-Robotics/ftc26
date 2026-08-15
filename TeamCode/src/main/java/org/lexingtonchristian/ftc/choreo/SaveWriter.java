package org.lexingtonchristian.ftc.choreo;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.internal.system.AppUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveWriter {

    protected File file;
    protected BufferedWriter writer;

    public SaveWriter(String filename) throws IOException {

        this.file = new File(filename);
        if (!file.isAbsolute()) this.file = new File(AppUtil.ROBOT_DATA_DIR, filename);

        File directory = file.getParentFile();
        AppUtil.getInstance().ensureDirectoryExists(directory);

        this.writer = new BufferedWriter(new FileWriter(file));

    }

    public void writeSnapshot(Snapshot... snapshots) throws IOException {

        for (Snapshot snapshot : snapshots) {
            writer.append(String.format("%1$s:%2$s,", snapshot.name, snapshot.value));
        }

        writer.append("\r\n");

    }

    public void close() {
        try {
            writer.close();
        } catch (IOException ignored) {

        }
    }

}
