package de.lasse.client.feature;

import org.reflections.Reflections;

import java.util.Set;

public class FeatureFactory {

    public void initAllFeatures() {
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
