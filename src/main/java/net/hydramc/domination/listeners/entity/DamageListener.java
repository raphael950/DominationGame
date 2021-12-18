package net.hydramc.domination.listeners.entity;

import net.hydramc.GameStats;
import net.hydramc.domination.Domination;
import net.hydramc.domination.utils.Utils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.Objects;

public class DamageListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent event) {

        if (!(Objects.requireNonNull(Domination.getGameInstance()).getGameStats() == GameStats.DURING))
            event.setCancelled(true);

    }

    @EventHandler
    public void onDamageByEntity(EntityDamageByEntityEvent event) {

        if (!(event.getEntity().getType() == EntityType.PLAYER)) {
            return;
        }

        Player player = (Player) event.getEntity();

        switch (Objects.requireNonNull(Domination.getGameInstance()).getGameStats()) {

            case WAITING:
                event.setCancelled(true);
                break;

            case DURING:
                event.setDamage(0);

                double damageValue = event.getFinalDamage();
                if (damageValue >= player.getHealth()) {
                    Entity attacker = event.getDamager();
                    Utils.death(player, attacker);
                }
                break;

        }

    }

}
