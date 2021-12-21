package net.hydramc.domination.listeners.player;

import net.hydramc.GameStats;
import net.hydramc.domination.Domination;
import net.hydramc.domination.Game;
import net.hydramc.domination.GameSetting;
import net.hydramc.domination.scoreboard.ScoreboardBuilder;
import net.hydramc.domination.utils.ActionBar;
import net.hydramc.domination.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Objects;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void event(PlayerJoinEvent event) {
        event.setJoinMessage("");

        final Game game = Domination.getGameInstance();
        final GameSetting gameSetting;

        if (game == null)
            return;
        gameSetting = game.getGameSetting();
        if (Bukkit.getOnlinePlayers().size() >= gameSetting.getMinPlayer())
            game.setGameStats(GameStats.STARTING);

        Player player = event.getPlayer();

        ScoreboardBuilder.build(player, game);

        switch (Objects.requireNonNull(Domination.getGameInstance()).getGameStats()) {
            case WAITING:
                Utils.spawn(player);
                ActionBar.sendGlobalActionBar("game.waitting.join_action_bar", player.getName(), Bukkit.getOnlinePlayers().size());
                break;

            case DURING:
                // TODO: Make the player spectating the game
                player.setGameMode(GameMode.SPECTATOR);
                break;

        }

    }

}
