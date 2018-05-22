package de.lasse.client.event;

import com.darkmagician6.eventapi.events.callables.EventCancellable;
import net.minecraft.network.Packet;

public class EventPacket extends EventCancellable {

    private Packet packet;
    private PacketMode mode;

    public EventPacket(Packet packet, PacketMode mode) {
        this.packet = packet;
        this.mode = mode;
    }

    public PacketMode getMode() {
        return mode;
    }

    public Packet getPacket() {
        return packet;
    }

    public void setPacket(Packet packet) {
        this.packet = packet;
    }

    public enum PacketMode {
        SENDING,
        RECEIVING
    }

}
