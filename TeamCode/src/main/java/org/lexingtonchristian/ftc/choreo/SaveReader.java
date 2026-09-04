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

        file = new File(filename);
        if (!file.isAbsolute()) file = new File(AppUtil.ROBOT_DATA_DIR, filename);

        File directory = file.getParentFile();
        AppUtil.getInstance().ensureDirectoryExists(directory);

        reader = new BufferedReader(new FileReader(file));

    }

    public Snapshot readSnapshot() throws IOException {

        long ms;
        Map<String, Double> values = new HashMap<>();

        String line = reader.readLine();
        if (line == null) return null;
        String[] timeAndValues = line.split("/");

        ms = Long.parseLong(timeAndValues[0]);
        String[] keyValuePairs = timeAndValues[1].split(",");
        for (String pair : keyValuePairs) {
            String[] keyValue = pair.split(":");
            String name = keyValue[0];
            double value = Double.parseDouble(keyValue[1]);
            values.put(name, value);
        }

        return new Snapshot(ms, values);

    }

    public void close() {
        try {
            reader.close();
        } catch (IOException ignored) {}
    }

}
