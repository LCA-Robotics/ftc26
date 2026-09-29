package org.lexingtonchristian.ftc.op.choreo;

import com.qualcomm.robotcore.eventloop.opmode.OpModeManager;
import com.qualcomm.robotcore.eventloop.opmode.OpModeRegistrar;

import org.firstinspires.ftc.robotcore.internal.opmode.OpModeMeta;

public class ChoreoRegistrar {

    private static OpModeMeta.Builder TELE = new OpModeMeta.Builder()
            .setFlavor(OpModeMeta.Flavor.TELEOP)
            .setSource(OpModeMeta.Source.ANDROID_STUDIO);

    private static OpModeMeta.Builder AUTO = new OpModeMeta.Builder()
            .setFlavor(OpModeMeta.Flavor.AUTONOMOUS)
            .setSource(OpModeMeta.Source.ANDROID_STUDIO);

    @OpModeRegistrar
    public static void register(OpModeManager manager) {

        registerPair(manager, "Alpha");
        registerPair(manager, "Bravo");
        registerPair(manager, "Charlie");
        registerPair(manager, "Delta");
        registerPair(manager, "Echo");

    }

    private static void registerPair(OpModeManager manager, String name) {
        String saveOpName = "Save to " + name;
        String loadOpName = "Load from " + name;
        String internalName = name.toLowerCase();
        manager.register(TELE.setName(saveOpName).build(), new SaveChoreo(internalName));
        manager.register(AUTO.setName(loadOpName).build(), new LoadChoreo(internalName));
    }

}
