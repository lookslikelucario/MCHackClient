package de.lasse.client.feature.impl.combat;

import com.darkmagician6.eventapi.EventTarget;
import de.lasse.client.Client;
import de.lasse.client.event.EventUpdate;
import de.lasse.client.feature.Feature;
import de.lasse.client.feature.value.impl.ValueBoolean;
import de.lasse.client.helper.CombatHelper;
import de.lasse.client.helper.TimeHelper;
import net.minecraft.entity.Entity;

public class TriggerBot extends Feature {

    private TimeHelper timeHelper;
    private CombatHelper combatHelper;
    private ValueBoolean newhitdelay;

    private Entity target;

    public TriggerBot() {
        super("TriggerBot");

        timeHelper = new TimeHelper();
        combatHelper = Client.getClient().getCombatHelper();

        newhitdelay = new ValueBoolean("NewHitDelay", true);

        addValue(newhitdelay);
    }

    @EventTarget
    public void onUpdate(EventUpdate eventUpdate) {
        target = mc.objectMouseOver.entityHit;

        if (combatHelper.canAttack(target) && combatHelper.heldsWeapon()) {
            if (newhitdelay.isEnabled()) {
                long cooldown = combatHelper.getCooldown(mc.player.getHeldItemMainhand().getItem());
                if (timeHelper.hasReached(cooldown)) {
                    attack();
                    timeHelper.reset();
                }
            } else {
                attack();
            }
        }
    }

    private void attack() {
        mc.playerController.attackEntity(mc.player, target);
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
