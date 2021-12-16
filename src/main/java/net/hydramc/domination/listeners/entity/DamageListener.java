package net.hydramc.domination.listeners.entity;

import net.hydramc.GameStats;
import net.hydramc.domination.Domination;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.Objects;

public class DamageListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent event) {

        if (!(Objects.requireNonNull(Domination.getGameInstance()).getGameStats().equals(GameStats.DURING))) {
            event.setCancelled(true);
        }

    }

}
