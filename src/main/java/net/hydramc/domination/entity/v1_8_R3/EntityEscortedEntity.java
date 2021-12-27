package net.hydramc.domination.entity.v1_8_R3;

import net.hydramc.util.FieldReflection;
import net.minecraft.server.v1_8_R3.EntityIronGolem;
import net.minecraft.server.v1_8_R3.EntityLiving;
import net.minecraft.server.v1_8_R3.PathfinderGoalSelector;
import net.minecraft.server.v1_8_R3.World;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityTargetEvent;

import java.util.List;

public class EntityEscortedEntity extends EntityIronGolem {

    private static void clearPathFinderGoalSelector(PathfinderGoalSelector pathfinderGoalSelector) {
        List<?> list;

        if (pathfinderGoalSelector == null)
            return;
        list = (List<?>) FieldReflection.getFieldValue("b", PathfinderGoalSelector.class, pathfinderGoalSelector);
        if (list != null)
            list.clear();
        list = (List<?>) FieldReflection.getFieldValue("c", PathfinderGoalSelector.class, pathfinderGoalSelector);
        if (list != null)
            list.clear();
    }

    public EntityEscortedEntity(World world) {
        super(world);
        clearPathFinderGoalSelector(this.goalSelector);
        clearPathFinderGoalSelector(this.targetSelector);
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

    public static EntityEscortedEntity spawnEntity(Location location, CreatureSpawnEvent.SpawnReason spawnReason) {
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
