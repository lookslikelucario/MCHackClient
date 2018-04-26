package de.lasse.client.feature.impl;

import com.darkmagician6.eventapi.EventTarget;
import de.lasse.client.event.EventUpdate;
import de.lasse.client.feature.Feature;
import org.lwjgl.input.Keyboard;

public class Sprint extends Feature {

    public Sprint() {
        super("Sprint", Keyboard.KEY_P);
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
