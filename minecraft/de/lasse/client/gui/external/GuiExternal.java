package de.lasse.client.gui.external;

import de.lasse.client.feature.Feature;
import de.lasse.client.feature.impl.gui.Gui;

import javax.swing.*;
import java.awt.*;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Objects;

import org.lwjgl.input.Keyboard;


public class GuiExternal {

    private static JFrame guiFrame;

    public static int savedPosX, savedPosY = 0;

    public GuiExternal() {

        /*
            TODO: Make a thread for this gui to "destroy" the instance for checking whether the gui is already "open"
            TODO: Right now the user is able to open the gui more than only ones
         */

        /*
            Create gui frame
         */
        guiFrame = new JFrame();

        /*
            Set position
         */
        guiFrame.setLocation(savedPosX, savedPosY);

        /*
            Stores number of features for iterating through them
         */
        int features = Feature.features.size();

        /*
            Create a container to apply a layout for it
         */
        Container guiContainer = guiFrame.getContentPane();

        /*
            For further sorting
         */
        guiContainer.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        /*
            Best layout for displaying buttons in a row
         */
        guiContainer.setLayout(new GridLayout(0, 1));

        /*
            Make the container bigger
         */
        guiContainer.setPreferredSize(new Dimension(200, 40 * features));

        /*
            Iterate through all features
         */
        for (Feature feature : Feature.features) {
            
            /*
                Blacklist Gui Feature
             */
            if (feature.getClass() == Gui.class) {
                continue;
            }

            /*
                Make a button for each feature
             */
            JToggleButton featureButton = new JToggleButton(feature.getFeatureName());

            // TODO: User should press any key to assign this key to a feature
            // For now only allow the alphabet
            String[] validKeyBinds = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};

            JComboBox<String> featureKeyBinds = new JComboBox<>(validKeyBinds);


            if (feature.isFeatureEnabled()) {
                featureButton.doClick();
            }

            featureKeyBinds.setSelectedItem(Keyboard.getKeyName(feature.getFeatureKeyBind()).toLowerCase());


            /*
                Apply an action listener for each button
             */
            featureButton.addActionListener(e -> feature.toggle());
            featureKeyBinds.addActionListener(e -> {
                int keybind = Keyboard.getKeyIndex(Objects.requireNonNull(featureKeyBinds.getSelectedItem()).toString().toUpperCase());
                feature.setFeatureKeyBind(keybind);
                System.out.println(feature.getFeatureName() + " was bound to " + keybind);
            });


            /*
                Add the current button and keybind to the frame
             */
            guiFrame.add(featureButton);
            guiFrame.add(featureKeyBinds);
        }

        /*
            Disable Gui feature when closing the external gui
         */
        guiFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Objects.requireNonNull(Feature.getFeatureByClass(Gui.class)).setEnabled(false);
            }
        });

        guiFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        /*
            Don't allow user to resize the frame
         */
        guiFrame.setResizable(false);

        /*
            Format the frame
         */
        guiFrame.pack();

        /*
            Finally show frame
         */
        guiFrame.setVisible(true);
    }

    public static JFrame getGuiFrame() {
        return guiFrame;
    }
}
