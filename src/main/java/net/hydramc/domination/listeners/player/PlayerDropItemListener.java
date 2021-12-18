package net.hydramc.domination.listeners.player;

import net.hydramc.GameStats;
import net.hydramc.domination.Domination;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import java.util.Objects;

public class PlayerDropItemListener implements Listener {

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {

        if (!Objects.requireNonNull(Domination.getGameInstance()).getGameStats().equals(GameStats.DURING))
            event.setCancelled(true);

    }

}
