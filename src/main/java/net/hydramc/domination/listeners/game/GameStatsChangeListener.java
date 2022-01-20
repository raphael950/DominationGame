package net.hydramc.domination.listeners.game;

import fr.mrcubee.langlib.Lang;
import net.hydramc.GameStats;
import net.hydramc.domination.Domination;
import net.hydramc.domination.entity.EscortedEntity;
import net.hydramc.domination.event.GameStatsChangeEvent;
import net.hydramc.domination.game.Game;
import net.hydramc.domination.player.PlayerManager;
import net.hydramc.domination.team.Team;
import net.hydramc.domination.team.TeamManager;
import net.hydramc.domination.utils.Locations;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class GameStatsChangeListener implements Listener {

    private final Game game = Domination.getGameInstance();
    private final TeamManager teamManager = game.getTeamManager();
    private final PlayerManager playerManager = game.getPlayerManager();

    @EventHandler
    public void event(GameStatsChangeEvent event) {

        GameStats lastGameStats = event.getCurrentGameStats();
        GameStats newGameStats = event.getNewGameStats();

        if (newGameStats == GameStats.STARTING) {
            // TODO: Decompte
            return;
        }

        if (newGameStats == GameStats.DURING) {
            game.getPlayerManager().setupPlayers();
            EscortedEntity escortedEntity = EscortedEntity.spawnEntity(Locations.getGolem());
            return;
        }

        if (newGameStats == GameStats.STOPPING) {

            for (Player player : Bukkit.getOnlinePlayers()) {

                if (player.getGameMode() == GameMode.SPECTATOR)
                    continue;
                player.setGameMode(GameMode.SPECTATOR);
                player.sendMessage(Lang.getMessage("game.stopping.message", "ERROR", true));
                Team team = teamManager.getTeam(player);

                // TODO: Add coins/exp to player

            }
            return;

        }

        if (newGameStats == GameStats.CLOSING) {
            playerManager.sendAllLobby();
            Bukkit.getServer().shutdown();
        }

    }

}
