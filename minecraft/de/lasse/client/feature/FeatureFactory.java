package de.lasse.client.feature;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;
import de.lasse.client.event.EventStart;
import org.reflections.Reflections;

import java.util.Set;

public class FeatureFactory {

    public FeatureFactory() {
        EventManager.register(this);
    }

    @EventTarget
    public void initAllFeatures(EventStart eventStart) {
        /*
            Instead of making a new instance of each feature manually,
            we scan the hole "features" package where all features are located at,
            iterate through them and make new instances.


            (Previously we had to do:

                new Feature1();
                new Feature2();
                new Feature3();
                ...)
         */
        Set<Class<? extends Feature>> featureClasses = new Reflections("de.lasse.client.feature.impl").getSubTypesOf(Feature.class);

        featureClasses.forEach(clazz -> {
            try {
                clazz.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                // Couldn't initialize feature
                e.printStackTrace();
            }
        });
    }

}
