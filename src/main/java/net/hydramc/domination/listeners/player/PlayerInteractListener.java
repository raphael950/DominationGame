package net.hydramc.domination.listeners.player;

import net.hydramc.GameStats;
import net.hydramc.domination.Domination;
import net.hydramc.domination.gui.TeamSelector;
import net.hydramc.domination.utils.GameUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {

        if (!GameStats.DURING.equals(Domination.getGameInstance().getGameStats())) {
            event.setCancelled(true);
        }

        Player player = event.getPlayer();

        if (event.getItem() == null)
            return;

        switch (event.getItem().getType()) {
            case BED:
                GameUtils.sendToLobby(player);
                break;
            case BANNER:
                new TeamSelector(player).open(player);
                break;
        }

    }

}
