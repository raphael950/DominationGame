package net.hydramc.domination.listeners.world;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;

public class BlockPhysicListener implements Listener {

    @EventHandler
    public void onBlockUpdate(BlockPhysicsEvent event) {

        if (event.getBlock().getType().equals(Material.LONG_GRASS))
            event.setCancelled(true);

    }

}
