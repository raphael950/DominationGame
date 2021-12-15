package net.hydramc.domination.listeners.player;

import net.hydramc.GameStats;
import net.hydramc.domination.Domination;
import net.hydramc.domination.Game;
import net.hydramc.domination.GameSetting;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void event(PlayerJoinListener event) {
        final Game game = Domination.getGameInstance();
        final GameSetting gameSetting;

        if (game == null)
            return;
        gameSetting = game.getGameSetting();
        if (Bukkit.getOnlinePlayers().size() >= gameSetting.getMinPlayer())
            game.setGameStats(GameStats.STARTING);
    }

}
