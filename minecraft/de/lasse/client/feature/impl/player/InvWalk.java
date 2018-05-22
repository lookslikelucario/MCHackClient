package de.lasse.client.feature.impl.player;

import com.darkmagician6.eventapi.EventTarget;
import de.lasse.client.event.EventUpdate;
import de.lasse.client.feature.Feature;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.client.gui.inventory.GuiInventory;
import org.lwjgl.input.Keyboard;

public class InvWalk extends Feature {

    private final float rotationSpeed;

    public InvWalk() {
        super("InvWalk");
        rotationSpeed = 3;
    }

    @EventTarget
    public void update(EventUpdate event) {
        if (mc.currentScreen instanceof GuiInventory || mc.currentScreen instanceof GuiChest || mc.currentScreen instanceof GuiCrafting) {

            // forward key
            mc.gameSettings.keyBindForward.pressed = Keyboard.isKeyDown(mc.gameSettings.keyBindForward.getKeyCode());

            // backward key
            mc.gameSettings.keyBindBack.pressed = Keyboard.isKeyDown(mc.gameSettings.keyBindBack.getKeyCode());

            // left key
            mc.gameSettings.keyBindLeft.pressed = Keyboard.isKeyDown(mc.gameSettings.keyBindLeft.getKeyCode());

            // right key
            mc.gameSettings.keyBindRight.pressed = Keyboard.isKeyDown(mc.gameSettings.keyBindRight.getKeyCode());

            // jump key
            if (Keyboard.isKeyDown(mc.gameSettings.keyBindJump.getKeyCode()) && mc.player.onGround) {
                mc.player.jump();
            }

            // sneak key
            mc.gameSettings.keyBindSneak.pressed = Keyboard.isKeyDown(mc.gameSettings.keyBindSneak.getKeyCode());

            // rotation
            if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
                mc.player.rotationPitch -= rotationSpeed;
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
                mc.player.rotationPitch += rotationSpeed;
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
                mc.player.rotationYaw += rotationSpeed;
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
                mc.player.rotationYaw -= rotationSpeed;
            }
        }
    }

}
