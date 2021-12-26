package net.hydramc.domination.listeners.player;

import fr.mrcubee.langlib.Lang;
import net.hydramc.GameStats;
import net.hydramc.domination.Domination;
import net.hydramc.domination.game.Game;
import net.hydramc.domination.gui.TeamSelector;
import net.hydramc.domination.team.Team;
import net.hydramc.domination.team.TeamManager;
import net.hydramc.domination.utils.GameUtils;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {

        final Game game = Domination.getGameInstance();
        final GameStats gameStats = Domination.getGameInstance().getGameStats();
        final Player player = event.getPlayer();

        if (player.getGameMode().equals(GameMode.CREATIVE))
            return;


        if (GameStats.DURING != gameStats) {
            event.setCancelled(true);
        }

        ItemStack item = event.getItem();
        Block clickedBlock = event.getClickedBlock();

        if (gameStats.equals(GameStats.WAITING)) {

            if (clickedBlock != null) {
                if (clickedBlock.getType().equals(Material.ENCHANTMENT_TABLE)) {
                    game.setGameStats(GameStats.DURING);
                    player.sendMessage(Lang.getMessage(player, "none", "§8» §7La partie a été lancée", true));
                    return;
                }
            }

            if (item == null)
                return;

            switch (event.getItem().getType()) {
                case BED:
                    GameUtils.sendToLobby(player);
                    break;
                case BANNER:
                    new TeamSelector(player).open(player);
                    break;
            }

            return;

        }

        if (gameStats.equals(GameStats.DURING)) {

            Team team = TeamManager.getTeam(player);

            if (clickedBlock == null)
                return;

            switch (clickedBlock.getType()) {

                case DARK_OAK_FENCE_GATE:
                    if (GameUtils.isInEnemyArea(team, clickedBlock.getLocation(), game))
                        event.setCancelled(true);
                    break;

                case NOTE_BLOCK:
                    event.setCancelled(true);
                    break;

            }

        }

    }

}
