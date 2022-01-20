package net.hydramc.domination.listeners.player;

import net.hydramc.domination.Domination;
import net.hydramc.domination.game.Game;
import net.hydramc.domination.game.GameSetting;
import net.hydramc.domination.player.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Objects;

public class PlayerQuitListener implements Listener {

    private final Game game = Domination.getGameInstance();
    private final PlayerManager playerManager = game.getPlayerManager();

    @EventHandler
    public void event(PlayerQuitEvent event) {
        event.setQuitMessage("");

        final GameSetting gameSetting;

        if (game == null)
            return;

        Player player = event.getPlayer();

        game.getPlayers().remove(player);
        game.getScoreboardManager().remove(player);

        game.getTeamManager().removeTeam(player);

        switch(Objects.requireNonNull(Domination.getGameInstance()).getGameStats()) {
            case WAITING:
                playerManager.sendGlobalActionBar("game.waiting.leave_action_bar", player.getName(), Bukkit.getOnlinePlayers().size());
                break;

            case DURING:
                if (Bukkit.getOnlinePlayers().size() == 1) {
                    // TODO: Close the server
                    return;
                }
                break;

        }

    }

}
