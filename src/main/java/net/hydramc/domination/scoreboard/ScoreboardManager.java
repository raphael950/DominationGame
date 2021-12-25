package net.hydramc.domination.scoreboard;

import net.hydramc.domination.Domination;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ScoreboardManager {

    public static void updateAllPlayers() {

        for (Player player : Bukkit.getOnlinePlayers()) {
            ScoreboardBuilder.build(player, Domination.getGameInstance());
        }

    }

}
