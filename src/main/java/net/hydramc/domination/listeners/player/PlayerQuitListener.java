package net.hydramc.domination.listeners.player;

import net.hydramc.domination.Domination;
import net.hydramc.domination.game.Game;
import net.hydramc.domination.game.GameSetting;
import net.hydramc.domination.scoreboard.ScoreboardBuilder;
import net.hydramc.domination.scoreboard.ScoreboardManager;
import net.hydramc.domination.team.TeamManager;
import net.hydramc.domination.utils.ActionBar;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Objects;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void event(PlayerQuitEvent event) {
        event.setQuitMessage("");

        final Game game = Domination.getGameInstance();
        final GameSetting gameSetting;

        if (game == null)
            return;

        Player player = event.getPlayer();
        ScoreboardBuilder.deleteBoard(player);

        TeamManager.removeTeam(player);

        ScoreboardManager.updateAllPlayers();

        switch(Objects.requireNonNull(Domination.getGameInstance()).getGameStats()) {
            case WAITING:
                ActionBar.sendGlobalActionBar("game.waiting.leave_action_bar", player.getName(), Bukkit.getOnlinePlayers().size());
                break;

            case DURING:
                if (Bukkit.getOnlinePlayers().size() == 1) {
                    // TODO: Map reset
                    return;
                }
                break;

        }

    }

}
