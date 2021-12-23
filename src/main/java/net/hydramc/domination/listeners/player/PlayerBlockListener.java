package net.hydramc.domination.listeners.player;

import fr.mrcubee.langlib.Lang;
import net.hydramc.GameStats;
import net.hydramc.domination.Domination;
import net.hydramc.domination.game.Game;
import net.hydramc.domination.team.TeamManager;
import net.hydramc.domination.utils.ActionBar;
import net.hydramc.domination.utils.Region;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class PlayerBlockListener implements Listener {

    private final Game gameInstance = Domination.getGameInstance();

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {

        Player player = event.getPlayer();

        if (player.getGameMode().equals(GameMode.CREATIVE) || !Domination.getGameInstance().getGameStats().equals(GameStats.DURING)) {
            return;
        }
        Location location = event.getBlock().getLocation();

        String team = TeamManager.getTeam(player);

        Region redRegion = gameInstance.getRedRegion();
        Region blueRegion = gameInstance.getBlueRegion();

        if ((team.equals("blue") && redRegion.isInSquare(location)) || (team.equals("red") && blueRegion.isInSquare(location))) {
            event.setCancelled(true);
            ActionBar.sendPlayerActionBar(player, Lang.getMessage(player, "game.during.not_assaut", "ERROR", true));
        }

    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {

        Player player = event.getPlayer();

        if (player.getGameMode().equals(GameMode.CREATIVE) || !Domination.getGameInstance().getGameStats().equals(GameStats.DURING)) {
            return;
        }
        Location location = event.getBlock().getLocation();

        String team = TeamManager.getTeam(player);

        Region redRegion = gameInstance.getRedRegion();
        Region blueRegion = gameInstance.getBlueRegion();

        if ((team.equals("blue") && redRegion.isInSquare(location)) || (team.equals("red") && blueRegion.isInSquare(location))) {
            event.setCancelled(true);
            ActionBar.sendPlayerActionBar(player, Lang.getMessage(player, "game.during.not_assaut", "ERROR", true));
        }

    }

}
