package org.lexingtonchristian.ftc.components;

import static java.lang.Math.abs;
import static java.lang.Math.cos;
import static java.lang.Math.max;
import static java.lang.Math.sin;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.lexingtonchristian.ftc.choreo.Snapshot;
import org.lexingtonchristian.ftc.util.Constants;

public class Drivetrain implements ChoreoDevice {

    private final DcMotorEx backLeft;
    private final DcMotorEx backRight;
    private final DcMotorEx frontLeft;
    private final DcMotorEx frontRight;

    private final IMU imu;

    private boolean fieldCentric = false;

    public Drivetrain(HardwareMap map) {

        backLeft = (DcMotorEx) map.get(DcMotor.class, Constants.BACK_LEFT);
        backRight = (DcMotorEx) map.get(DcMotor.class,Constants.BACK_RIGHT);
        frontLeft = (DcMotorEx) map.get(DcMotor.class,Constants.FRONT_LEFT);
        frontRight = (DcMotorEx) map.get(DcMotor.class, Constants.FRONT_RIGHT);

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

        double y1 = y0;
        double x1 = x0;
        if (fieldCentric) {
            y1 = ( y0 * cos(heading) ) - ( x0 * sin(heading) );
            x1 = ( y0 * sin(heading) ) + ( x0 * cos(heading) );
        }

        double m = max(abs(y0) + abs(x0) + abs(r0), 1);

        double backLeftPower   = (y1 - x1 - r0) / m;
        double backRightPower  = (y1 + x1 + r0) / m;
        double frontLeftPower  = (y1 + x1 - r0) / m;
        double frontRightPower = (y1 - x1 + r0) / m;

        backLeft.setPower(backLeftPower);
        backRight.setPower(backRightPower);
        frontLeft.setPower(frontLeftPower);
        frontRight.setPower(frontRightPower);

    }

    @Override
    public Snapshot writeValues(Snapshot snapshot) {
        snapshot.addCapture(Constants.BACK_LEFT, backLeft.getPower());
        snapshot.addCapture(Constants.BACK_RIGHT, backRight.getPower());
        snapshot.addCapture(Constants.FRONT_LEFT, frontLeft.getPower());
        snapshot.addCapture(Constants.FRONT_RIGHT, frontRight.getPower());
        return snapshot;
    }

    @Override
    public void readValues(Snapshot snapshot) {
        backLeft.setPower(snapshot.getValue(Constants.BACK_LEFT));
        backRight.setPower(snapshot.getValue(Constants.BACK_RIGHT));
        frontLeft.setPower(snapshot.getValue(Constants.FRONT_LEFT));
        frontRight.setPower(snapshot.getValue(Constants.FRONT_RIGHT));
    }

}
