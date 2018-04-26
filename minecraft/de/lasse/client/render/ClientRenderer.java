package de.lasse.client.render;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;
import de.lasse.client.Client;
import de.lasse.client.event.EventRender;
import net.minecraft.client.Minecraft;

public class ClientRenderer {

    public ClientRenderer() {
        EventManager.register(this);
    }

    @EventTarget
    public void renderName(EventRender eventRender) {
        Minecraft.getMinecraft().fontRendererObj.drawString(Client.NAME, 5, 5, 0x000000);
    }


}
