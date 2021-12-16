package net.hydramc.domination.listeners.entity;

import net.hydramc.domination.Domination;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.Objects;

public class DamageListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent event) {

        switch (Objects.requireNonNull(Domination.getGameInstance()).getGameStats()) {

            case WAITING:
                if (event.getEntity().getType() == EntityType.PLAYER) {
                    event.setCancelled(true);
                }

            case DURING:
                if (!(event.getEntityType() == EntityType.PLAYER)) {
                    return;
                }
                double damageValue = event.getFinalDamage();
                Player player = (Player) event.getEntity();
                if (damageValue >= player.getHealth()) {
                    // cancel event and "kill" the player
                }

        }

    }

}
