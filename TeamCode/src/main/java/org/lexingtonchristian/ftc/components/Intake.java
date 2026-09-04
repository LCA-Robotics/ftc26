package org.lexingtonchristian.ftc.components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.lexingtonchristian.ftc.choreo.Snapshot;
import org.lexingtonchristian.ftc.util.Constants;
import org.lexingtonchristian.ftc.util.MathHelper;

public class Intake implements ChoreoDevice {

    private final DcMotorEx motor;

    private boolean active;
    private long startTime;
    private long activeDuration;

    public Intake(HardwareMap map) {

        motor = (DcMotorEx) map.get(DcMotor.class, Constants.INTAKE);
        motor.setDirection(DcMotorSimple.Direction.FORWARD);

        active = false;
        startTime = 0;
        activeDuration = 0;

    }

    public void setActive(boolean active) {
        boolean prev = this.active;
        this.active = active;
        if (active && !prev) {
            startTime = System.currentTimeMillis();
            activeDuration = 0;
        }
    }

    public void tick() {

        if (!active) {
            motor.setPower(0.0);
            return;
        }

        if (activeDuration >= Constants.INTAKE_SPINUP_MS) {
            motor.setVelocity(Constants.INTAKE_VELOCITY);
            return;
        }

        activeDuration = System.currentTimeMillis() - startTime;

        motor.setVelocity(MathHelper.easeInOutSine(
                0,
                Constants.INTAKE_VELOCITY,
                (double) activeDuration / (double) Constants.INTAKE_SPINUP_MS
        ));

    }

    @Override
    public Snapshot writeValues(Snapshot snapshot) {
        snapshot.addCapture(Constants.INTAKE, motor.getPower());
        return snapshot;
    }

    @Override
    public void readValues(Snapshot snapshot) {
        motor.setPower(snapshot.getValue(Constants.INTAKE));
    }

}
