package org.lexingtonchristian.ftc.op.choreo;

import org.lexingtonchristian.ftc.choreo.Choreographer;
import org.lexingtonchristian.ftc.op.tele.PrimaryTeleOp;

public class SaveChoreo extends PrimaryTeleOp {

    private final Choreographer choreographer;

    public SaveChoreo(String name) {
        choreographer = new Choreographer(name);
    }

    @Override
    public void start() {
        choreographer.initWriteTask(drivetrain, intake);
    }

    @Override
    public void stop() {
        choreographer.close();
    }

}
