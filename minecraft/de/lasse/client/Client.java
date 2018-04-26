package de.lasse.client;

import de.lasse.client.feature.FeatureFactory;
import de.lasse.client.management.InputManager;
import de.lasse.client.render.ClientRenderer;
import de.lasse.client.render.FeatureRenderer;
import org.lwjgl.opengl.Display;

public class Client {

    public static final String NAME = "Client"; // Until I find a better name

    private Client client;

    private FeatureFactory featureFactory;
    private FeatureRenderer featureRenderer;
    private InputManager inputManager;
    private ClientRenderer clientRenderer;

    public Client() {
        client = this;

        featureFactory = new FeatureFactory();
        featureFactory.initAllFeatures();

        featureRenderer = new FeatureRenderer();
        inputManager = new InputManager();

        clientRenderer = new ClientRenderer();

        /* Set display title */
        Display.setTitle(NAME);


    }

    public Client getClient() {
        return client;
    }

}
