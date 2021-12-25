package net.hydramc.domination.listeners.player;

import fr.mrcubee.langlib.Lang;
import net.hydramc.GameStats;
import net.hydramc.domination.Domination;
import net.hydramc.domination.game.Game;
import net.hydramc.domination.team.Team;
import net.hydramc.domination.team.TeamManager;
import net.hydramc.domination.utils.ActionBar;
import net.hydramc.domination.utils.GameUtils;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class PlayerBlockListener implements Listener {

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {

        final Player player = event.getPlayer();

        if (player.getGameMode().equals(GameMode.CREATIVE))
            return;

        final Game game = Domination.getGameInstance();

        if (!GameStats.DURING.equals(game.getGameStats())) {
            event.setCancelled(true);
            return;
        }

        final Location location = event.getBlock().getLocation();
        final Team team = TeamManager.getTeam(player);

        if (team == null)
            return;

        if (GameUtils.isEnemyArea(team, location, game)) {
            event.setCancelled(true);
            ActionBar.sendPlayerActionBar(player, Lang.getMessage(player, "game.during.not_assaut", "ERROR", true));
        }

    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {

        final Player player = event.getPlayer();

        if (player.getGameMode().equals(GameMode.CREATIVE))
            return;

        final Game game = Domination.getGameInstance();

        if (!GameStats.DURING.equals(game.getGameStats())) {
            event.setCancelled(true);
            return;
        }

        final Location location = event.getBlock().getLocation();
        final Team team = TeamManager.getTeam(player);

        if (team == null)
            return;

        if (GameUtils.isEnemyArea(team, location, game)) {
            event.setCancelled(true);
            ActionBar.sendPlayerActionBar(player, Lang.getMessage(player, "game.during.not_assaut", "ERROR", true));
        }

    }

}
