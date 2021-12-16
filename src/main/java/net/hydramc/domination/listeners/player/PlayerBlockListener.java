package net.hydramc.domination.listeners.player;

import net.hydramc.GameStats;
import net.hydramc.domination.Domination;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.Objects;

public class PlayerBlockListener implements Listener {

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {

        if (!(Objects.requireNonNull(Domination.getGameInstance()).getGameStats().equals(GameStats.DURING))) {
            event.setCancelled(true);
        }

    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {

        if (!(Objects.requireNonNull(Domination.getGameInstance()).getGameStats().equals(GameStats.DURING))) {
            event.setCancelled(true);
        }

    }

}
