package de.lasse.client.feature.impl.combat;

import com.darkmagician6.eventapi.EventTarget;
import de.lasse.client.event.EventPacket;
import de.lasse.client.feature.Feature;
import de.lasse.client.feature.value.impl.ValueNumber;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketEntityVelocity;

public class Velocity extends Feature {

    private final ValueNumber horiModifier;
    private final ValueNumber vertModifier;

    public Velocity() {
        super("Velocity");
        horiModifier = new ValueNumber("Horizontal", 50, 0, 100, 5);
        vertModifier = new ValueNumber("Vertical", 50, 0, 100, 5);
        this.addValue(horiModifier);
        this.addValue(vertModifier);
    }

    @EventTarget
    public void sendPacket(EventPacket event){
        //checks if the packet comes from the server and is not sent by the client
        if(event.getMode().equals(EventPacket.PacketMode.RECEIVING)){
            Packet packet = event.getPacket();
            //checks if the packet is a velocity packet
            if(packet instanceof SPacketEntityVelocity){
                SPacketEntityVelocity velocityPacket = (SPacketEntityVelocity) packet;
                //checks if the packets target is the player
                if(mc.player.getEntityId() == velocityPacket.getEntityID()){
                    velocityPacket.setMotionX(velocityPacket.getMotionX() / 100 * horiModifier.getCurrentValue());
                    velocityPacket.setMotionZ(velocityPacket.getMotionZ() / 100 * horiModifier.getCurrentValue());
                    velocityPacket.setMotionY(velocityPacket.getMotionY() / 100 * vertModifier.getCurrentValue());
                }
            }
        }
    }

}
