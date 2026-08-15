package org.lexingtonchristian.ftc.choreo;

import org.firstinspires.ftc.robotcore.internal.system.AppUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SaveReader {

    protected File file;
    protected BufferedReader reader;

    public SaveReader(String filename) throws IOException {

        this.file = new File(filename);
        if (!file.isAbsolute()) this.file = new File(AppUtil.ROBOT_DATA_DIR, filename);

        File directory = file.getParentFile();
        AppUtil.getInstance().ensureDirectoryExists(directory);

        this.reader = new BufferedReader(new FileReader(file));

    }

    public Map<String, Double> readSnapshot() throws IOException {

        Map<String, Double> snapshot = new HashMap<>();

        String line = reader.readLine();
        if (line == null) return null;
        String[] raw = line.split(",");

        for (String motor : raw) {
            String[] info = motor.split(":");
            if (info.length < 2) break;
            snapshot.put(info[0], Double.parseDouble(info[1]));
        }

        return snapshot;

    }

}
