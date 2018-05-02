package de.lasse.client.gui.external;

import de.lasse.client.feature.Feature;
import de.lasse.client.feature.impl.gui.Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Objects;

public class GuiExternal {

    public GuiExternal() {

        /*
            TODO: Make a thread for this gui to "destroy" the instance for checking whether the gui is already "open"
            TODO: Right now the user is able to open the gui more than only ones

            TODO: Replace button with radio buttons or slt...
            TODO: Display active features as active
         */

        /*
            Create gui frame
         */
        JFrame guiFrame = new JFrame();

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
        for (int i = 0; i < features; i++) {

            /*
                Blacklist Gui Feature
             */
            if(Feature.features.get(i).getClass() == Gui.class) {
                continue;
            }

            /*
                Make a button for each feature
             */
            JButton featureButton = new JButton(Feature.features.get(i).getFeatureName());

            /*
               Make a effectively final temp variable
             */
            int finalI = i;

            /*
                Apply an action listener for each button
             */
            featureButton.addActionListener(e -> Feature.features.get(finalI).toggle());

            /*
                Add the current button to the frame
             */
            guiFrame.add(featureButton);
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
}
