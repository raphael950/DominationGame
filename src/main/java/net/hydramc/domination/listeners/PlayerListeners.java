package net.hydramc.domination.listeners;

import net.hydramc.domination.Domination;
import net.hydramc.domination.gui.TeamSelector;
import net.hydramc.domination.utils.ActionBar;
import net.hydramc.domination.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Objects;

public class PlayerListeners implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        switch(Objects.requireNonNull(Domination.getGameInstance()).getGameStats()) {
            case WAITING:
                ActionBar.sendGlobalActionBar("game.waitting.join_action_bar", player.getName());
                Utils.giveJoinItems(player);
                break;

            case DURING:
                // spec
                break;
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        switch(Objects.requireNonNull(Domination.getGameInstance()).getGameStats()) {
            case WAITING:
                ActionBar.sendGlobalActionBar("game.waitting.leave_action_bar", player.getName());
                break;

            case DURING:
                // spec
                break;
        }
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
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
