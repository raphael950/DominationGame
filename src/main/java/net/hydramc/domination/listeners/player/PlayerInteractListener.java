package net.hydramc.domination.listeners.player;

import net.hydramc.GameStats;
import net.hydramc.domination.Domination;
import net.hydramc.domination.gui.TeamSelector;
import net.hydramc.domination.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Objects;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {

        if (!(Objects.requireNonNull(Domination.getGameInstance()).getGameStats().equals(GameStats.DURING))) {
            event.setCancelled(true);
        }

        Player player = event.getPlayer();

        if (event.getItem().getType() == null) {
            return;
        }

        switch (event.getItem().getType()) {
            case BED:
                Utils.sendToLobby(player);
                break;
            case BANNER:
                new TeamSelector().open(player);
                break;
        }

    }

}
