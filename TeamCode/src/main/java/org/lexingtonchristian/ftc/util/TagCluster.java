package org.lexingtonchristian.ftc.util;

public class TagCluster {

    public final int LEFT_END;
    public final int LEFT_MID;
    public final int RIGHT_MID;
    public final int RIGHT_END;

    public TagCluster(int start) {
        LEFT_END = start;
        LEFT_MID = start + 1;
        RIGHT_MID = start + 2;
        RIGHT_END = start + 3;
    }

}
