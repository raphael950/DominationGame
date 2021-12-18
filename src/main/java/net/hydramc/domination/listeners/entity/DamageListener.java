package net.hydramc.domination.listeners.entity;

import net.hydramc.domination.Domination;
import net.hydramc.domination.utils.Utils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.Objects;

public class DamageListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {

        if (!(event.getEntityType() == EntityType.PLAYER)) {
            return;
        }

        switch (Objects.requireNonNull(Domination.getGameInstance()).getGameStats()) {

            case WAITING:
                event.setCancelled(true);

            case DURING:
                event.setDamage(0);

                Player player = (Player) event.getEntity();

                double damageValue = event.getFinalDamage();
                if (damageValue >= player.getHealth()) {
                    Entity attacker = event.getDamager();
                    Utils.death(player, attacker);
                }

        }

    }

}
