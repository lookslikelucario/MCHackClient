package de.lasse.client.feature.impl.world;

import de.lasse.client.feature.Feature;
import net.minecraft.client.settings.GameSettings;

public class FullBright extends Feature {

    private float userGamma;

    public FullBright() {
        super("FullBright", -1);
    }

    @Override
    public void onEnable() {
        // Backup current gamma value so we can restore it after disabling the module
        userGamma = mc.gameSettings.getOptionFloatValue(GameSettings.Options.GAMMA);
        // TODO: Replace this with another method! Currently there are still shadow effects
        mc.gameSettings.gammaSetting = 20;
        super.onEnable();
    }

    @Override
    public void onDisable() {
        // Restore gamma value
        mc.gameSettings.gammaSetting = userGamma;

        super.onDisable();
    }
}
