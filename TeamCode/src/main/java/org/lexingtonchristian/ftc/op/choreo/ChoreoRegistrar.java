package org.lexingtonchristian.ftc.op.choreo;

import com.qualcomm.robotcore.eventloop.opmode.OpModeManager;
import com.qualcomm.robotcore.eventloop.opmode.OpModeRegistrar;

public class ChoreoRegistrar {

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
        manager.register(saveOpName, new SaveChoreo(internalName));
        manager.register(loadOpName, new LoadChoreo(internalName));
    }

}
