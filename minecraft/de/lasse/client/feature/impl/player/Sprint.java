package de.lasse.client.feature.impl.player;

import com.darkmagician6.eventapi.EventTarget;
import de.lasse.client.event.EventUpdate;
import de.lasse.client.feature.Feature;
import de.lasse.client.feature.value.impl.ValueBoolean;
import de.lasse.client.feature.value.impl.ValueNumber;

public class Sprint extends Feature {

    public Sprint() {
        super("Sprint");
        addValue(new ValueBoolean("test1", false));
        addValue(new ValueNumber("test2", 10, 0, 100, 10));
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
