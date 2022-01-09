package net.hydramc.domination.listeners.player;

import net.hydramc.GameStats;
import net.hydramc.domination.Domination;
import net.hydramc.domination.game.Game;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {

    private final Game game = Domination.getGameInstance();

    @EventHandler
    public void onMove(PlayerMoveEvent event) {

        if (GameStats.DURING != game.getGameStats())
            return;

        Player player = event.getPlayer();
        /*Team team = game.getTeamManager().getTeam(player);

        if (team == null)
            return;*/

        Location location = player.getLocation();

        /* TODO: Rework this code. Require Team class completed !
        if (playerData.getInBase()) {
            // player is already in his base.
            if (!GameUtils.isInArea(team, location, game)) {
                ActionBar.sendPlayerActionBar(player, Lang.getMessage(player, "game.during.left_base", "ERROR", true));
                playerData.setInBase(false);
            }

        } else if (GameUtils.isInArea(team, location, game)) {
            ActionBar.sendPlayerActionBar(player, Lang.getMessage(player, "game.during.enter_base", "ERROR", true));
            playerData.setInBase(true);
            playerData.setInEnemyBase(false);
        }

        if (playerData.getInEnemyBase()) {
            // player is already in enemy base.
            if (!GameUtils.isInEnemyArea(team, location, game)) {
                ActionBar.sendPlayerActionBar(player, Lang.getMessage(player, "game.during.left_enemyBase", "ERROR", true));
                playerData.setInEnemyBase(false);
            }
        } else if (GameUtils.isInEnemyArea(team, location, game)) {
            ActionBar.sendPlayerActionBar(player, Lang.getMessage(player, "game.during.left_enemyBase", "ERROR", true));
            playerData.setInEnemyBase(false);
        }
         */
    }

}
