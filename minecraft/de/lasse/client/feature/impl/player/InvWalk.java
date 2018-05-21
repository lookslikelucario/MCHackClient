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
    public void update(EventUpdate event){
        if(mc.currentScreen instanceof GuiInventory || mc.currentScreen instanceof GuiChest || mc.currentScreen instanceof GuiCrafting){

            //forward key
            if(Keyboard.isKeyDown(mc.gameSettings.keyBindForward.getKeyCode())){
                mc.gameSettings.keyBindForward.pressed = true;
            }else{
                mc.gameSettings.keyBindForward.pressed = false;
            }

            //backward key
            if(Keyboard.isKeyDown(mc.gameSettings.keyBindBack.getKeyCode())){
                mc.gameSettings.keyBindBack.pressed = true;
            }else{
                mc.gameSettings.keyBindBack.pressed = false;
            }

            //left key
            if(Keyboard.isKeyDown(mc.gameSettings.keyBindLeft.getKeyCode())){
                mc.gameSettings.keyBindLeft.pressed = true;
            }else{
                mc.gameSettings.keyBindLeft.pressed = false;
            }

            //right key
            if(Keyboard.isKeyDown(mc.gameSettings.keyBindRight.getKeyCode())){
                mc.gameSettings.keyBindRight.pressed = true;
            }else{
                mc.gameSettings.keyBindRight.pressed = false;
            }

            //jump key
            if(Keyboard.isKeyDown(mc.gameSettings.keyBindJump.getKeyCode())){
                if(mc.player.onGround){
                    mc.player.jump();
                }
            }

            //rotation
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
