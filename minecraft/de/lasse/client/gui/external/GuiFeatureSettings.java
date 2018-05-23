package de.lasse.client.gui.external;

import de.lasse.client.feature.Feature;
import de.lasse.client.feature.value.Value;
import de.lasse.client.feature.value.impl.ValueBoolean;
import de.lasse.client.feature.value.impl.ValueNumber;

import javax.swing.*;
import java.awt.*;

public class GuiFeatureSettings {

    private static JFrame guiFrame;

    public GuiFeatureSettings(Feature feature) {

        /*
            Create gui frame
         */
        guiFrame = new JFrame();

        /*
            Stores number of features for size assignment
         */
        int settingsLength = feature.featureValues.size();

        /*
            Create a container to apply a layout for it
         */
        Container guiContainer = guiFrame.getContentPane();

        /*
            For further sorting
         */
        guiContainer.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        /*
            Best layout for displaying components in a row
         */
        guiContainer.setLayout(new GridLayout(0, 1));

        /*
            Make the container bigger
         */
        guiContainer.setPreferredSize(new Dimension(200, 40 * settingsLength));

        /*
            Iterate through all values
         */
        for (Value value : feature.featureValues) {
            switch (value.getValueType()) {
                case NUMBER:
                    addNumberField((ValueNumber) value);
                    break;
                case BOOLEAN:
                    addBooleanField((ValueBoolean) value);
                    break;
                default:
                    // This shouldn't happen!
                    System.err.println("Cannot find value type of: " + feature.getFeatureName());
                    break;
            }
        }

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

    private void addBooleanField(ValueBoolean value) {
        JCheckBox checkBox = new JCheckBox(value.getValueName(), value.isEnabled());
        checkBox.addActionListener(e -> value.setEnabled(!value.isEnabled()));
        guiFrame.add(checkBox);
    }

    private void addNumberField(ValueNumber value) {
        JLabel sliderTitle = new JLabel(value.getValueName());
        JSlider slider = new JSlider(value.getMinValue(), value.getMaxValue(), value.getCurrentValue());
        slider.setExtent(value.getIncrement());
        slider.addChangeListener(e -> value.setCurrentValue(slider.getValue()));
        guiFrame.add(sliderTitle);
        guiFrame.add(slider);
    }
}
