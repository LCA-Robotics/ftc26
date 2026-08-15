package org.lexingtonchristian.ftc.util;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.IMU;

public class Constants {

    public static final double MAX_ACCELERATION = 2.0; // TODO: test & refine as necessary

    public static final IMU.Parameters IMU_PARAMETERS = new IMU.Parameters(new RevHubOrientationOnRobot(
            RevHubOrientationOnRobot.LogoFacingDirection.BACKWARD,
            RevHubOrientationOnRobot.UsbFacingDirection.UP
    ));

}
