package net.hydramc.domination.listeners.entity;

import net.hydramc.GameStats;
import net.hydramc.domination.Domination;
import net.hydramc.domination.team.TeamManager;
import net.hydramc.domination.utils.GameUtils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        GameStats gameStats = Domination.getGameInstance().getGameStats();
        if (!GameStats.DURING.equals(gameStats))
            event.setCancelled(true);
    }

    @EventHandler
    public void onDamageByEntity(EntityDamageByEntityEvent event) {

        final GameStats gameStats = Domination.getGameInstance().getGameStats();

        if (!GameStats.DURING.equals(gameStats)) {
            event.setCancelled(true);
            return;
        }

        final Entity victimEntity = event.getEntity();
        final Entity damagerEntity = event.getDamager();

        final double damageValue = event.getFinalDamage();

        switch (victimEntity.getType()) {

            case PLAYER:
                break;

            case ENDER_CRYSTAL:
                event.setCancelled(true);
                // TODO: Target is endercrystal
                damagerEntity.sendMessage("En dev");
                return;

            default:
                return;

        }

        final Player victim = (Player) victimEntity;

        if (damageValue >= victim.getHealth()) {
            event.setDamage(0);
            GameUtils.death(victim, damagerEntity);
            return;
        }

        if (!damagerEntity.getType().equals(EntityType.PLAYER))
            return;

        final Player damager = (Player) damagerEntity;

        if (TeamManager.getTeam(victim).equals(TeamManager.getTeam(damager)))
            event.setCancelled(true);

    }

}
