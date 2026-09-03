package org.lexingtonchristian.ftc.op.choreo.load;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.lexingtonchristian.ftc.choreo.SaveReader;
import org.lexingtonchristian.ftc.components.Drivetrain;

import java.io.IOException;

public abstract class LoadChoreo extends OpMode {

    private Drivetrain drivetrain;

    @Override
    public void init() {

        drivetrain = new Drivetrain(hardwareMap);

    }

    @Override
    public void loop() {

    }

    @Override
    public void stop() {

    }

    public abstract String getName();

}
