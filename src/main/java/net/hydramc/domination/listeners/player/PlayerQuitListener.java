package net.hydramc.domination.listeners.player;

import net.hydramc.GameStats;
import net.hydramc.domination.Domination;
import net.hydramc.domination.Game;
import net.hydramc.domination.GameSetting;
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
        gameSetting = game.getGameSetting();
        if ((Bukkit.getOnlinePlayers().size() - 1) < gameSetting.getMinPlayer())
            game.setGameStats(GameStats.WAITING);

        Player player = event.getPlayer();
        switch(Objects.requireNonNull(Domination.getGameInstance()).getGameStats()) {
            case WAITING:
                ActionBar.sendGlobalActionBar("game.waitting.leave_action_bar", player.getName(), Bukkit.getOnlinePlayers().size());
                break;

            case DURING:
                // spec
                break;

        }

    }

}
