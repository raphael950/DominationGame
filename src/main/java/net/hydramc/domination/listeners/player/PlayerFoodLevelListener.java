package net.hydramc.domination.listeners.player;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class PlayerFoodLevelListener implements Listener {

    @EventHandler
    public void onFeed(FoodLevelChangeEvent event) {

        if (!(event.getEntity().getType() == EntityType.PLAYER))
            return;

        event.setFoodLevel(20);

    }

}
