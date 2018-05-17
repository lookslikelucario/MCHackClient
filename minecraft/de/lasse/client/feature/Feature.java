package de.lasse.client.feature;

import com.darkmagician6.eventapi.EventManager;
import net.minecraft.client.Minecraft;

import java.util.ArrayList;

public class Feature {

    public static final ArrayList<Feature> features = new ArrayList<>();
    public static final ArrayList<Feature> activeFeatures = new ArrayList<>();
    protected final Minecraft mc = Minecraft.getMinecraft();
    private String featureName;
    private int featureKeyBind;
    private boolean featureEnabled;

    public Feature(String name, int keyBind) {
        featureName = name;
        featureKeyBind = keyBind;

        features.add(this);
        System.out.println("Registered feature: " + name);
    }

    /*
        Use this constructor if we don't have any keybind we want to assign to the feature
     */
    public Feature(String name) {
        featureName = name;

        features.add(this);
        System.out.println("Registered feature: " + name);
    }

    public void onEnable() {
        EventManager.register(this);
        activeFeatures.add(this);
    }

    public void onDisable() {
        EventManager.unregister(this);
        activeFeatures.remove(this);
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

    public static Feature getFeatureByClass(Class<? extends Feature> clazz) {
        for (Feature feature : features) {
            if (feature.getClass() == clazz) {
                return feature;
            }
        }
        return null;
    }

    public static Feature getFeatureByName(String featureName) {
        for (Feature feature : features) {
            if (feature.getFeatureName().equals(featureName)) {
                return feature;
            }
        }
        return null;
    }
}
