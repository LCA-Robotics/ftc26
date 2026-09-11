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

    public Intake(HardwareMap map) {
        motor = (DcMotorEx) map.get(DcMotor.class, Constants.INTAKE);
        motor.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void set(boolean active) {
        motor.setVelocity(active ? Constants.INTAKE_VELOCITY : 0);
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
