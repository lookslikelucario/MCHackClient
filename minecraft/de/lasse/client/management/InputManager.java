package de.lasse.client.management;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;
import de.lasse.client.event.EventOnKeyInput;
import de.lasse.client.feature.Feature;
import org.lwjgl.input.Keyboard;

import java.util.Arrays;

public class InputManager {

    private final int[] keyBinds;

    public InputManager() {
        int i = 0;
        keyBinds = new int[Feature.features.size()];
        for (Feature feature : Feature.features) {
            keyBinds[i] = feature.getFeatureKeyBind();
            i++;
        }
        System.out.println("Keybinds: " + Arrays.toString(keyBinds));
        EventManager.register(this);
    }


    @EventTarget
    public void onKeyInput(EventOnKeyInput eventOnKeyInput) {
        int eventKey = Keyboard.getEventKey();

        // TODO: We don't need to check for changed keybinds on every key input
        int i = 0;
        for (Feature feature : Feature.features) {
            keyBinds[i] = feature.getFeatureKeyBind();
            i++;
        }
        // TODO: Clean this up! There might be some unnecessary checks
        for (int keyBind : keyBinds) {
            if (eventKey == keyBind) {
                for (Feature feature : Feature.features) {
                    if (feature.getFeatureKeyBind() == eventKey && Keyboard.isKeyDown(eventKey)) {
                        feature.toggle();
                        System.out.println("Feature " + feature.getFeatureName() + " was toggled!");
                    }
                }
            }
        }
    }

}
