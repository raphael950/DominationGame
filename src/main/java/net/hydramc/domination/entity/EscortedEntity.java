package net.hydramc.domination.entity;

import net.hydramc.domination.team.Team;
import net.hydramc.util.Version;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.IronGolem;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.lang.reflect.Method;

public interface EscortedEntity extends IronGolem {

    public Team getTeamTarget();
    public void setTeamTarget(Team team);

    public static EscortedEntity spawnEntity(Location location, CreatureSpawnEvent.SpawnReason spawnReason) {
        final Class<?> craftEscortedEntityClass;
        final Method method;

        craftEscortedEntityClass = Version.getNMSClass("net.hydramc.domination.entity.%s.CraftEscortedEntity");
        if (craftEscortedEntityClass == null)
            return null;
        try {
            method = craftEscortedEntityClass.getDeclaredMethod("spawnEntity", Location.class, CreatureSpawnEvent.SpawnReason.class);
            method.setAccessible(true);
            return (EscortedEntity) method.invoke(null, location, spawnReason);
        } catch (Exception ignored) {}
        return null;
    }

    public static EscortedEntity spawnEntity(Location location) {
        return spawnEntity(location, CreatureSpawnEvent.SpawnReason.CUSTOM);
    }

}
