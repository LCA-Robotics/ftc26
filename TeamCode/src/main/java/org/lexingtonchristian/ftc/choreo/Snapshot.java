package org.lexingtonchristian.ftc.choreo;

import java.util.HashMap;
import java.util.Map;

public class Snapshot {

    public final long time;

    public final Map<String, Double> values;

    public Snapshot(long time) {
        this.time = time;
        values = new HashMap<>();
    }

    public Snapshot(long time, Map<String, Double> values) {
        this.time = time;
        this.values = values;
    }

    public void addCapture(String name, double val) {
        values.put(name, val);
    }

    public double getValue(String name) {
        return values.getOrDefault(name, 0.0);
    }

}
