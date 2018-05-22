package de.lasse.client.helper;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;

public class CombatHelper {

    private Minecraft mc;

    public CombatHelper() {
        mc = Minecraft.getMinecraft();
    }

    /*
        Checks if the entity is attackable by checking the distance from the player to the entity.
        This distance shouldn't be over 3.7 otherwise the player would swing the item but won't
        hit the enemy
     */
    public boolean canAttack(Entity entity) {
        return entity.getDistanceToEntity(mc.player) < 3.7
                && isEntityInstanceWanted(entity)
                && mc.objectMouseOver.entityHit != null;

    }

    /*
        Checks if the instance of the entity is an instance we want to attack in most cases
        This ignores any entities like xp orbs, boats, minecarts and stuff like that
     */
    private boolean isEntityInstanceWanted(Entity entity) {
        return entity instanceof EntityPlayer
                || entity instanceof EntityAnimal
                || entity instanceof EntityCreature;
    }

    /*
        Checks if the player is holding a weapon.
        Weapons are swords, axes and pickaxes.
        Bows are ignored at the moment because we are using this method for triggerbot and we don't
        wanna hit an entity with a bow
     */
    public boolean heldsWeapon() {
        return mc.player.getHeldItemMainhand().getItem() instanceof ItemSword
                || mc.player.getHeldItemMainhand().getItem() instanceof ItemAxe
                || mc.player.getHeldItemMainhand().getItem() instanceof ItemPickaxe;
    }

    /*
        Returns the attack cooldown of the given weapon
     */
    public long getCooldown(Item item) {
        // TODO: Axes with different material have different cooldowns
        // 250 is default cooldown
        return item instanceof ItemSword ? 600 : item instanceof ItemAxe ? 1250 : item instanceof ItemPickaxe ? 850 : 250;
    }
}
