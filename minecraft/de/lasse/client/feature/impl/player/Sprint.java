package de.lasse.client.feature.impl.player;

import com.darkmagician6.eventapi.EventTarget;
import de.lasse.client.event.EventUpdate;
import de.lasse.client.feature.Feature;

public class Sprint extends Feature {

    public Sprint() {
        super("Sprint", -1);
    }

    @EventTarget
    public void onUpdate(EventUpdate eventUpdate) {
        mc.player.setSprinting(true);
    }

    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Override
    public void onDisable() {
        mc.player.setSprinting(false);
        super.onDisable();
    }
}
