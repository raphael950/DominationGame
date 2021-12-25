package net.hydramc.domination.listeners.player;

import fr.mrcubee.langlib.Lang;
import net.hydramc.GameStats;
import net.hydramc.domination.Domination;
import net.hydramc.domination.game.Game;
import net.hydramc.domination.utils.ActionBar;
import net.hydramc.domination.team.Region;
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
        final Game game = Domination.getGameInstance();

        if (player.getGameMode().equals(GameMode.CREATIVE) || !GameStats.DURING.equals(game.getGameStats())) {
            return;
        }

        final Location location = event.getBlock().getLocation();

        final Region redRegion = game.getRedRegion();
        final Region blueRegion = game.getBlueRegion();

        if (game.getRed().isMember(player)) {
            if (game.getBlueRegion().isInCircle(location)) {
                event.setCancelled(true);
                ActionBar.sendPlayerActionBar(player, Lang.getMessage(player, "game.during.not_assaut", "ERROR", true));
            }
        } else if (game.getRedRegion().isInCircle(location)) {
            event.setCancelled(true);
            ActionBar.sendPlayerActionBar(player, Lang.getMessage(player, "game.during.not_assaut", "ERROR", true));
        }

    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {

        final Player player = event.getPlayer();
        final Game game = Domination.getGameInstance();

        if (player.getGameMode().equals(GameMode.CREATIVE) || !GameStats.DURING.equals(game.getGameStats())) {
            return;
        }

        final Location location = event.getBlock().getLocation();

        final Region redRegion = game.getRedRegion();
        final Region blueRegion = game.getBlueRegion();

        if (game.getRed().isMember(player)) {
            if (game.getBlueRegion().isInCircle(location)) {
                event.setCancelled(true);
                ActionBar.sendPlayerActionBar(player, Lang.getMessage(player, "game.during.not_assaut", "ERROR", true));
            }
        } else if (game.getRedRegion().isInCircle(location)) {
            event.setCancelled(true);
            ActionBar.sendPlayerActionBar(player, Lang.getMessage(player, "game.during.not_assaut", "ERROR", true));
        }

    }

}
