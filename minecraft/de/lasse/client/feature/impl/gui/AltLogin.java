package de.lasse.client.feature.impl.gui;

import de.lasse.client.feature.Feature;
import de.lasse.client.gui.external.GuiLogin;
import org.lwjgl.input.Keyboard;

public class AltLogin extends Feature {

    public AltLogin() {
        super("AltLogin", Keyboard.KEY_K);
    }

    @Override
    public void onEnable() {
        new GuiLogin();
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
