package net.hydramc.domination.listeners.player;

import net.hydramc.GameStats;
import net.hydramc.domination.Domination;
import net.hydramc.domination.game.Game;
import net.hydramc.domination.game.GameSetting;
import net.hydramc.domination.player.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private final Game game = Domination.getGameInstance();
    private final PlayerManager playerManager = game.getPlayerManager();

    @EventHandler
    public void event(PlayerJoinEvent event) {
        event.setJoinMessage("");

        final GameSetting gameSetting;

        if (game == null)
            return;
        gameSetting = game.getGameSetting();
        if (Bukkit.getOnlinePlayers().size() >= gameSetting.getMinPlayer())
            game.setGameStats(GameStats.STARTING);

        Player player = event.getPlayer();

        switch (game.getGameStats()) {
            case WAITING:
                game.getPlayers().add(player);
                playerManager.spawnWaiting(player);
                playerManager.sendGlobalActionBar("game.waiting.join_action_bar", player.getName(), Bukkit.getOnlinePlayers().size());
                break;

            case DURING:
                // TODO: Make the player spectating the game
                player.setGameMode(GameMode.SPECTATOR);
                break;

        }

    }

}
