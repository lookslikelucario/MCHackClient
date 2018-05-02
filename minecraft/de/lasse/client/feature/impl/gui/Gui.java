package de.lasse.client.feature.impl.gui;

import de.lasse.client.feature.Feature;
import de.lasse.client.gui.external.GuiExternal;
import org.lwjgl.input.Keyboard;

public class Gui extends Feature {

    public Gui() {
        super("Gui", Keyboard.KEY_L);
    }

    @Override
    public void onEnable() {
        new GuiExternal();
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
