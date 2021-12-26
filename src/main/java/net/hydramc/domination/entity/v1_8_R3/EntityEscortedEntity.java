package net.hydramc.domination.entity.v1_8_R3;

import net.minecraft.server.v1_8_R3.EntityIronGolem;
import net.minecraft.server.v1_8_R3.EntityLiving;
import net.minecraft.server.v1_8_R3.World;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityTargetEvent;

public class EntityEscortedEntity extends EntityIronGolem {

    public EntityEscortedEntity(World world) {
        super(world);
    }

    @Override
    public void setGoalTarget(EntityLiving entityliving, EntityTargetEvent.TargetReason reason, boolean fireEvent) {

    }

    @Override
    public CraftEntity getBukkitEntity() {
        if (this.bukkitEntity == null)
            this.bukkitEntity = new CraftEscortedEntity(this.world.getServer(), this);
        return this.bukkitEntity;
    }

    public static EntityEscortedEntity spawn(Location location, CreatureSpawnEvent.SpawnReason spawnReason) {
        final World world;
        final EntityEscortedEntity result;

        if (location == null || location.getWorld() == null || spawnReason == null)
            return null;
        world = ((CraftWorld) location.getWorld()).getHandle();
        if (world == null)
            return null;
        result = new EntityEscortedEntity(world);
        result.setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
        world.addEntity(result, spawnReason);
        return result;
    }
}
