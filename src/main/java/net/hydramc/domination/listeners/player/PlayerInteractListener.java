package net.hydramc.domination.listeners.player;

import fr.mrcubee.langlib.Lang;
import net.hydramc.GameStats;
import net.hydramc.domination.Domination;
import net.hydramc.domination.game.Game;
import net.hydramc.domination.gui.TeamSelector;
import net.hydramc.domination.utils.GameUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {

        final Game game = Domination.getGameInstance();

        if (!GameStats.DURING.equals(game.getGameStats())) {
            event.setCancelled(true);
        }

        Player player = event.getPlayer();

        // Temp game start
        if (event.getClickedBlock().getType().equals(Material.ENCHANTMENT_TABLE)) {
            if (!game.getGameStats().equals(GameStats.WAITING))
                player.sendMessage(Lang.getMessage("none", "§8» §cLa partie est déjà en cours", true));
            else if (game.setGameStats(GameStats.DURING))
                player.sendMessage(Lang.getMessage(player, "none", "§8» §7La partie a été lancée", true));
            else
                player.sendMessage(Lang.getMessage(player, "none", "§8» §cLa partie ne peut pas encore être lancée", true));
            return;
        }

        if (event.getItem() == null)
            return;

        switch (event.getItem().getType()) {
            case BED:
                GameUtils.sendToLobby(player);
                break;
            case BANNER:
                new TeamSelector(player).open(player);
                break;
        }

    }

}
