package de.lasse.client.file;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;
import com.darkmagician6.eventapi.types.Priority;
import de.lasse.client.Client;
import de.lasse.client.event.EventShutDown;
import de.lasse.client.event.EventStart;
import de.lasse.client.feature.Feature;
import de.lasse.client.feature.impl.player.Sprint;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.lwjgl.input.Keyboard;

import java.io.*;

public class FeatureFile {

    private File featureFile;

    public FeatureFile() {
        EventManager.register(this, EventStart.class);
        EventManager.register(this, EventShutDown.class);

        featureFile = new File(Client.getClient().getClientDir(), "features.cfg");
        if (!featureFile.exists()) {
            try {
                featureFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveFeatureState() {
        try {
            PrintWriter printWriter = new PrintWriter(new FileWriter(featureFile));
            for (Feature feature : Feature.features) {
                printWriter.println(feature.getFeatureName() + ":" + String.valueOf(feature.isFeatureEnabled()) + ":" + feature.getFeatureKeyBind());
            }
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFeatureState() {
        LineIterator lineIterator = null;
        try {
            lineIterator = FileUtils.lineIterator(featureFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            assert lineIterator != null;
            while (lineIterator.hasNext()) {
                Feature feature;
                String[] splitted = lineIterator.next().split(":");
                feature = Feature.getFeatureByName(splitted[0]);
                assert feature != null;
                feature.setFeatureKeyBind(Integer.parseInt(splitted[2]));
                feature.setEnabled(Boolean.valueOf(splitted[1]));
            }
        } finally {
            assert lineIterator != null;
            lineIterator.close();
        }
    }

    @EventTarget
    public void onShutDown(EventShutDown eventShutDown) {
        saveFeatureState();
    }

    @EventTarget(Priority.LOW)
    public void onStart(EventStart eventStart) {
        readFeatureState();
    }
}
