package org.lexingtonchristian.ftc.util;

import static java.lang.Math.PI;
import static java.lang.Math.abs;
import static java.lang.Math.cos;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class MathHelper {

    public static final double EPSILON = 1E-6;

    public static double clamp(double val, double min, double max) {
        return max(min, min(val, max));
    }

    public static boolean equal(double a, double b) {
        return abs(a - b) < EPSILON;
    }

    public static double easeInOutSine(double x0, double x1, double t) {
        return - ( ( (x1 - x0) * ( cos( PI * t ) - 1 ) ) / 2 ) + x0;
    }

}
