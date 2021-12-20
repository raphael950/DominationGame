package net.hydramc.domination.listeners.game;

import net.hydramc.GameStats;
import net.hydramc.domination.event.GameStatsChangeEvent;
import net.hydramc.domination.team.TeamManager;
import net.hydramc.domination.utils.Locations;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class GameStatsChangeListener implements Listener {
    
    @EventHandler
    public void event(GameStatsChangeEvent event) {

        GameStats lastGameStats = event.getCurrentGameStats();
        GameStats newGameStats = event.getNewGameStats();


        if (newGameStats == GameStats.DURING) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (!TeamManager.hasTeam(player)) {
                    TeamManager.setRandomTeam(player);
                }
                String team = TeamManager.getTeam(player);
                player.teleport(Locations.getSpawn(team));
            }
        }

        if (lastGameStats == GameStats.DURING) {

            // TODO: End of the game

        }

    }

}
