package de.lasse.client.gui.external;

import de.lasse.client.alt.AltLoginThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiLogin {

    /*
        TODO: Replace hardcoded component positions with a layout
     */
    public GuiLogin() {

        /*
            Create gui frame
         */
        JFrame guiFrame = new JFrame();

        guiFrame.setSize(new Dimension(225, 220));
        guiFrame.setLayout(null);

        /*
            Email and pass field
         */
        JTextField emailField = new JTextField("Email");
        JTextField passField = new JTextField("Password");

        emailField.setBounds(10, 50, 200, 30);
        passField.setBounds(10, 90, 200, 30);


        /*
            Submit button
         */
        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(55, 150, 112, 30);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AltLoginThread(emailField.getText(), passField.getText()).run();
            }
        });

        /*
            Add components to frame
         */
        guiFrame.add(emailField);
        guiFrame.add(passField);
        guiFrame.add(submitButton);

        /*
            Properties
         */
        guiFrame.setResizable(false);
        guiFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        /*
            Make frame visible
         */
        guiFrame.setVisible(true);
    }
}
