package de.lasse.client.file;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;
import com.darkmagician6.eventapi.types.Priority;
import de.lasse.client.Client;
import de.lasse.client.event.EventShutDown;
import de.lasse.client.event.EventStart;
import de.lasse.client.feature.Feature;

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

    private void saveFeatureToggleState() {
        try {
            PrintWriter printWriter = new PrintWriter(new FileWriter(featureFile));
            for (Feature feature : Feature.features) {
                printWriter.println(feature.getFeatureName() + ":" + String.valueOf(feature.isFeatureEnabled()));
            }
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFeatureToggleState() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(featureFile));
            for (String s; ( s = bufferedReader.readLine() ) != null; ) {
                String[] splitted = s.split(":");
                Feature feature = Feature.getFeatureByName(splitted[0]);
                assert feature != null;
                feature.setEnabled(Boolean.valueOf(splitted[1]));
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @EventTarget
    public void onShutDown(EventShutDown eventShutDown) {
        saveFeatureToggleState();
    }

    @EventTarget(Priority.LOW)
    public void onStart(EventStart eventStart) {
        readFeatureToggleState();
    }
}
