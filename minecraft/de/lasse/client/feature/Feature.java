package de.lasse.client.feature;

import com.darkmagician6.eventapi.EventManager;
import net.minecraft.client.Minecraft;

import java.util.ArrayList;

public class Feature {

    public static ArrayList<Feature> features = new ArrayList<>();
    public Minecraft mc = Minecraft.getMinecraft();
    private String featureName;
    private int featureKeyBind;
    private boolean featureEnabled;

    public Feature(String name, int keyBind) {
        featureName = name;
        featureKeyBind = keyBind;

        features.add(this);
        System.out.println("Registered feature: " + name);
    }

    public void onEnable() {
        EventManager.register(this);
    }

    public void onDisable() {
        EventManager.unregister(this);
    }

    public void toggle() {
        setEnabled(!featureEnabled);
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public int getFeatureKeyBind() {
        return featureKeyBind;
    }

    public void setFeatureKeyBind(int featureKeyBind) {
        this.featureKeyBind = featureKeyBind;
    }

    public boolean isFeatureEnabled() {
        return featureEnabled;
    }

    public void setEnabled(boolean enabled) {
        if (enabled) {
            onEnable();
            featureEnabled = true;
        } else {
            onDisable();
            featureEnabled = false;
        }
    }




}
