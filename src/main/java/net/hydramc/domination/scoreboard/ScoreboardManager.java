package net.hydramc.domination.scoreboard;

import net.hydramc.domination.Domination;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ScoreboardManager {

    public void updateAllPlayers() {
        // TODO: Loop for update all player's sidebar scoreboard (use ScoreboardBuilder.build(player, game))

        for (Player player : Bukkit.getOnlinePlayers()) {
            ScoreboardBuilder.build(player, Domination.getGameInstance());
        }

    }

}
