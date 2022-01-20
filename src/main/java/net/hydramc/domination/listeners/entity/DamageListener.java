package net.hydramc.domination.listeners.entity;

import net.hydramc.GameStats;
import net.hydramc.domination.Domination;
import net.hydramc.domination.game.Game;
import net.hydramc.domination.player.DeathManager;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageListener implements Listener {

    private final Game game = Domination.getGameInstance();

    @EventHandler
    public void onDamage(EntityDamageEvent event) {

        if (GameStats.DURING != game.getGameStats()) {
            event.setCancelled(true);
            return;
        }

        if (!(event.getEntity() instanceof Player))
            return;

        final Player victim = (Player) event.getEntity();
        final double damageValue = event.getFinalDamage();

        if (damageValue >= victim.getHealth()) {

            event.setDamage(0);

            if (event instanceof EntityDamageByEntityEvent) {
                return;
            }
            new DeathManager(game).death(victim);

        }
    }

    @EventHandler
    public void onDamageByEntity(EntityDamageByEntityEvent event) {

        if (GameStats.DURING != game.getGameStats()) {
            event.setCancelled(true);
            return;
        }

        final Entity victimEntity = event.getEntity();
        final Entity damagerEntity = event.getDamager();

        if (victimEntity.getType() == EntityType.IRON_GOLEM) {
            event.setCancelled(true);
            return;
        }

        if (!(victimEntity instanceof Player)) return;

        final Player victim = (Player) victimEntity;

        if (damagerEntity instanceof Player) {
            Player damager = (Player) damagerEntity;
            if (game.getTeamManager().getTeam(victim) == game.getTeamManager().getTeam(damager)) {
                event.setCancelled(true);
                return;
            }
        }
        if (event.getFinalDamage() >= victim.getHealth()) {
            new DeathManager(game).death(victim, damagerEntity);
        }

    }

}
