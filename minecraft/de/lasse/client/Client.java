package de.lasse.client;

import com.darkmagician6.eventapi.EventManager;
import de.lasse.client.event.EventStart;
import de.lasse.client.feature.FeatureFactory;
import de.lasse.client.file.FeatureFile;
import de.lasse.client.management.InputManager;
import de.lasse.client.render.ClientRenderer;
import de.lasse.client.render.FeatureRenderer;
import org.apache.commons.lang3.SystemUtils;
import org.lwjgl.opengl.Display;

import java.io.File;

public class Client {

    public static final String NAME = "Client"; // Until I find a better name

    private static Client client;

    private File clientDir;

    private FeatureFactory featureFactory;
    private FeatureRenderer featureRenderer;
    private InputManager inputManager;
    private ClientRenderer clientRenderer;

    public Client() {

        client = this;

        // Consider using getUserDir instead of getUserHome
        clientDir = new File(SystemUtils.getUserHome(), NAME);

        if (!clientDir.exists()) {
            clientDir.mkdirs();
        }

        new FeatureFile();


        featureFactory = new FeatureFactory();

        featureRenderer = new FeatureRenderer();

        clientRenderer = new ClientRenderer();

        EventManager.call(new EventStart());

        inputManager = new InputManager();

        /* Set display title */
        Display.setTitle(NAME);


    }

    public static Client getClient() {
        return client;
    }

    public File getClientDir() {
        return clientDir;
    }
}
