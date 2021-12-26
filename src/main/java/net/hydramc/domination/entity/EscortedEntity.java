package net.hydramc.domination.entity;

import net.hydramc.domination.team.Team;
import org.bukkit.Location;
import org.bukkit.entity.IronGolem;
import org.bukkit.event.entity.CreatureSpawnEvent;

public interface EscortedEntity extends IronGolem {

    public Team getTeamTarget();
    public void setTeamTarget(Team team);
    
    public static EscortedEntity spawnEntity(Location location, CreatureSpawnEvent.SpawnReason spawnReason) {
        //TODO: Call CraftEscortedEntity static NMS method.
        return null;
    }

    public static EscortedEntity spawnEntity(Location location) {
        return spawnEntity(location, CreatureSpawnEvent.SpawnReason.CUSTOM);
    }

}
