package org.lexingtonchristian.ftc.util;

public class MotorHelper {

    public static int ticksForAngle(double angle) {
        return (int) (Constants.TICKS_PER_REV * (angle / 360.0));
    }

}
