package net.hydramc.domination.listeners.player;

import net.hydramc.GameStats;
import net.hydramc.domination.Domination;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {

        if (GameStats.DURING != Domination.getGameInstance().getGameStats())
            event.setCancelled(true);

    }

}
