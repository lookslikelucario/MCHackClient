package de.lasse.client.render;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;
import de.lasse.client.event.EventRender;
import de.lasse.client.feature.Feature;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

public class FeatureRenderer {

    public FeatureRenderer() {
        EventManager.register(this);
    }

    @EventTarget
    public void renderFeatures(EventRender eventRender) {
        ScaledResolution resolution = new ScaledResolution(Minecraft.getMinecraft());
        int y = 0;
        for (Feature feature : Feature.features) {
            if (feature.isFeatureEnabled()) {
                int x = resolution.getScaledWidth() - Minecraft.getMinecraft().fontRendererObj.getStringWidth(feature.getFeatureName()) - 5;
                y += 10;
                renderFeature(feature, x, y);
            }
        }
    }

    private void renderFeature(Feature feature, int x, int y) {
        Minecraft.getMinecraft().fontRendererObj.drawString(feature.getFeatureName(), x, y, 0x000000);
    }

}
