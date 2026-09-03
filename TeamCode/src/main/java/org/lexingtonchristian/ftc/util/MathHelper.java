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

    public static double easeInOutSine(double x0, double x1, double t) {
        double dx = x1 - x0;
        return - ( dx * ( cos( PI * ( ( t - x0 ) / dx ) ) - 1 ) ) / 2 ;
    }

}
