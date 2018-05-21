package de.lasse.client.feature.impl.combat;

import com.darkmagician6.eventapi.EventTarget;
import de.lasse.client.Client;
import de.lasse.client.event.EventUpdate;
import de.lasse.client.feature.Feature;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.util.EnumHand;

public class TriggerBot extends Feature {

    public TriggerBot() {
        super("TriggerBot");
    }

    @EventTarget
    public void onUpdate(EventUpdate eventUpdate) {
        Entity target = mc.objectMouseOver.entityHit;
        EnumHand usedHand = EnumHand.MAIN_HAND;
        if(Client.getClient().getCombatHelper().canAttack(target)) {
            mc.player.swingArm(usedHand);
            mc.player.connection.sendPacket(new CPacketUseEntity(target));
        }
    }

    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
