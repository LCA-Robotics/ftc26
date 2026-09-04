package org.lexingtonchristian.ftc.components;

import org.lexingtonchristian.ftc.choreo.Snapshot;

public interface ChoreoDevice {

    Snapshot writeValues(Snapshot snapshot);
    void readValues(Snapshot snapshot);

}
