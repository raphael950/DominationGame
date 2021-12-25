package net.hydramc.domination.listeners.entity;

import net.hydramc.GameStats;
import net.hydramc.domination.Domination;
import net.hydramc.domination.game.Game;
import net.hydramc.domination.team.Team;
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

        final Game game = Domination.getGameInstance();

        if (!GameStats.DURING.equals(game.getGameStats())) {
            event.setCancelled(true);
            return;
        }

        final Entity victimEntity = event.getEntity();
        final Entity damagerEntity = event.getDamager();

        final Team red = game.getRed();
        final Team blue = game.getBlue();
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

        // Here victim is Player

        if (damageValue >= victim.getHealth()) {
            event.setDamage(0);
            GameUtils.death(victim, damagerEntity);
            return;
        }

        if (!damagerEntity.getType().equals(EntityType.PLAYER)) return;

        // Here damager is Player

        final Player damager = (Player) damagerEntity;

        if (red.isMember(damager) && red.isMember(victim) || blue.isMember(damager) && blue.isMember(victim))
            event.setCancelled(true);

    }

}
