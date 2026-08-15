package org.lexingtonchristian.ftc.components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.lexingtonchristian.ftc.util.Constants;

import static java.lang.Math.*;

public class Drivetrain {

    private final DcMotorEx backLeft;
    private final DcMotorEx backRight;
    private final DcMotorEx frontLeft;
    private final DcMotorEx frontRight;

    private final IMU imu;

    private boolean fieldCentric = false;

    public Drivetrain(HardwareMap map) {

        backLeft = (DcMotorEx) map.get(DcMotor.class, "backLeft");
        backRight = (DcMotorEx) map.get(DcMotor.class, "backRight");
        frontLeft = (DcMotorEx) map.get(DcMotor.class, "frontLeft");
        frontRight = (DcMotorEx) map.get(DcMotor.class, "frontRight");

        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);

        imu = map.get(IMU.class, "imu");
        imu.initialize(Constants.IMU_PARAMETERS);

    }

    public void toggleFieldCentric() {
        fieldCentric = !fieldCentric;
    }

    public void resetHeading() {
        imu.resetYaw();
    }

    public void drive(double x0, double y0, double r0) {

        double heading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

        double x1 = x0;
        double y1 = y0;
        if (fieldCentric) {
            x1 = ( x0 * cos(heading) ) - ( y0 * sin(heading) );
            y1 = ( x0 * sin(heading) ) + ( y0 * cos(heading) );
        }

        double m = max(abs(x0) + abs(y0) + abs(r0), 1);

        double backLeftPower   = (x1 - y1 - r0) / m;
        double backRightPower  = (x1 + y1 + r0) / m;
        double frontLeftPower  = (x1 + y1 - r0) / m;
        double frontRightPower = (x1 - y1 + r0) / m;

        backLeft.setPower(backLeftPower);
        backRight.setPower(backRightPower);
        frontLeft.setPower(frontLeftPower);
        frontRight.setPower(frontRightPower);

    }

}
