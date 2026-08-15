package org.lexingtonchristian.ftc.util;

import static java.lang.Math.*;

public class MathHelper {

    public static final double EPSILON = 1E-6;

    public static double clamp(double val, double min, double max) {
        return max(min, min(val, max));
    }

    public static boolean equal(double a, double b) {
        return abs(a - b) < EPSILON;
    }

    public static double easeInCubic(double t) {
        return t * t * t;
    }

    public static double easeOutCubic(double t) {
        return 1 - pow(1 - t, 3);
    }

}
