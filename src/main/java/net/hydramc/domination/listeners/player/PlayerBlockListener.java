package net.hydramc.domination.listeners.player;

import fr.mrcubee.langlib.Lang;
import net.hydramc.GameStats;
import net.hydramc.domination.Domination;
import net.hydramc.domination.game.Game;
import net.hydramc.domination.player.PlayerManager;
import net.hydramc.domination.team.Team;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class PlayerBlockListener implements Listener {

    private final Game game = Domination.getGameInstance();
    private final PlayerManager playerManager = game.getPlayerManager();

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {

        final Player player = event.getPlayer();

        if (player.getGameMode().equals(GameMode.CREATIVE))
            return;

        if (!GameStats.DURING.equals(game.getGameStats()) || player.getWorld().getName().equals("world")) {
            event.setCancelled(true);
            return;
        }

        final Location location = event.getBlock().getLocation();
        final Team team = game.getTeamManager().getTeam(player);

        if (playerManager.isInEnemyArea(team, location, game)) {
            event.setCancelled(true);
            playerManager.sendActionBar(player, Lang.getMessage(player, "game.during.not_assaut", "ERROR", true));
        }

    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {

        final Player player = event.getPlayer();

        if (player.getGameMode().equals(GameMode.CREATIVE))
            return;

        final Game game = Domination.getGameInstance();

        if (!GameStats.DURING.equals(game.getGameStats()) || player.getWorld().getName().equals("world")) {
            event.setCancelled(true);
            return;
        }

        final Location location = event.getBlock().getLocation();
        final Team team = game.getTeamManager().getTeam(player);

        if (playerManager.isInEnemyArea(team, location, game)) {
            event.setCancelled(true);
            playerManager.sendActionBar(player, Lang.getMessage(player, "game.during.not_assaut", "ERROR", true));
        }

    }

}
