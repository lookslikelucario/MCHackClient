package de.lasse.client.file;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;
import com.darkmagician6.eventapi.types.Priority;
import de.lasse.client.Client;
import de.lasse.client.event.EventShutDown;
import de.lasse.client.event.EventStart;
import de.lasse.client.gui.external.GuiExternal;

import java.io.*;

public class GuiFile {

    private File guiFile;

    public GuiFile() {
        EventManager.register(this, EventStart.class);
        EventManager.register(this, EventShutDown.class);

        guiFile = new File(Client.getClient().getClientDir(), "gui.cfg");
        if (!guiFile.exists()) {
            try {
                guiFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveGuiState() {
        try {
            PrintWriter printWriter = new PrintWriter(new FileWriter(guiFile));
            printWriter.println(GuiExternal.getGuiFrame().getX() + ":" + GuiExternal.getGuiFrame().getY());
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadGuiState() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(guiFile));
            String[] splitted = bufferedReader.readLine().split(":");
            GuiExternal.savedPosX = Integer.parseInt(splitted[0]);
            GuiExternal.savedPosY = Integer.parseInt(splitted[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @EventTarget
    public void onShutDown(EventShutDown eventShutDown) {
        saveGuiState();
    }

    @EventTarget(Priority.LOW)
    public void onStart(EventStart eventStart) {
        loadGuiState();
    }
}
