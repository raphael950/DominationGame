package net.hydramc.domination.listeners.player;

import net.hydramc.GameStats;
import net.hydramc.domination.Domination;
import net.hydramc.domination.Game;
import net.hydramc.domination.GameSetting;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void event(PlayerQuitEvent event) {
        final Game game = Domination.getGameInstance();
        final GameSetting gameSetting;

        if (game == null)
            return;
        gameSetting = game.getGameSetting();
        if ((Bukkit.getOnlinePlayers().size() - 1) < gameSetting.getMinPlayer())
            game.setGameStats(GameStats.WAITING);
    }

}
