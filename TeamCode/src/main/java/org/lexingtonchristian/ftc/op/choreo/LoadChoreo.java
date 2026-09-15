package org.lexingtonchristian.ftc.op.choreo;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.lexingtonchristian.ftc.choreo.Choreographer;
import org.lexingtonchristian.ftc.components.Drivetrain;
import org.lexingtonchristian.ftc.components.Intake;

public class LoadChoreo extends OpMode {

    private final Choreographer choreographer;

    private volatile Drivetrain drivetrain;
    private volatile Intake intake;

    public LoadChoreo(String name) {
        choreographer = new Choreographer(name);
    }

    @Override
    public void init() {
        drivetrain = new Drivetrain(hardwareMap);
        intake = new Intake(hardwareMap);
    }

    @Override
    public void start() {
        choreographer.initReadTask(drivetrain, intake);
    }

    @Override
    public void loop() {
        Thread.yield();
    }

    @Override
    public void stop() {
        choreographer.close();
    }

}
