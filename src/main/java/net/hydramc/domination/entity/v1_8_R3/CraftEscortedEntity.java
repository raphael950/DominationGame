package net.hydramc.domination.entity.v1_8_R3;

import net.hydramc.domination.entity.EscortedEntity;
import net.hydramc.domination.event.EscortedEntityChangeTeamTarget;
import net.hydramc.domination.team.Team;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftIronGolem;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.util.Objects;

public class CraftEscortedEntity extends CraftIronGolem implements EscortedEntity {

    private Team teamTarget;

    public CraftEscortedEntity(CraftServer server, EntityEscortedEntity entity) {
        super(server, entity);
        this.teamTarget = null;
    }

    @Override
    public Team getTeamTarget() {
        return this.teamTarget;
    }

    @Override
    public void setTeamTarget(Team team) {
        final EscortedEntityChangeTeamTarget event = new EscortedEntityChangeTeamTarget(this, this.teamTarget, teamTarget);

        if (Objects.hashCode(team) != Objects.hashCode(teamTarget)) {
            Bukkit.getPluginManager().callEvent(event);
            if (event.isCancelled())
                return;
        }
        this.teamTarget = event.getTarget();
    }

    public static CraftEscortedEntity spawnEntity(Location location, CreatureSpawnEvent.SpawnReason spawnReason) {
        final EntityEscortedEntity entityEscortedTarget = EntityEscortedEntity.spawnEntity(location, spawnReason);

        if (entityEscortedTarget == null)
            return null;
        return ((CraftEscortedEntity) entityEscortedTarget.getBukkitEntity());
    }
}
