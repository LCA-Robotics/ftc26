package org.lexingtonchristian.ftc.util;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.IMU;

public class Constants {

    public static final IMU.Parameters IMU_PARAMETERS = new IMU.Parameters(new RevHubOrientationOnRobot(
            RevHubOrientationOnRobot.LogoFacingDirection.FORWARD,
            RevHubOrientationOnRobot.UsbFacingDirection.UP
    ));

    public static final String BACK_LEFT = "backLeft";
    public static final String BACK_RIGHT = "backRight";
    public static final String FRONT_LEFT = "frontLeft";
    public static final String FRONT_RIGHT = "frontRight";

    public static final String INTAKE = "intake";

    public static final int INTAKE_VELOCITY = 1200;

    public static final TagCluster RED_SCORING = new TagCluster(30);
    public static final TagCluster RED_AUDIENCE = new TagCluster(34);
    public static final TagCluster BLUE_AUDIENCE = new TagCluster(38);
    public static final TagCluster BLUE_SCORING = new TagCluster(42);

}
