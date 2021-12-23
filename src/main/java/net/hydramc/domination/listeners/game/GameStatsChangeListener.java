package net.hydramc.domination.listeners.game;

import fr.mrmicky.fastinv.ItemBuilder;
import net.hydramc.GameStats;
import net.hydramc.domination.Domination;
import net.hydramc.domination.event.GameStatsChangeEvent;
import net.hydramc.domination.team.TeamManager;
import net.hydramc.domination.utils.Locations;
import net.hydramc.domination.utils.Utils;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GameStatsChangeListener implements Listener {
    
    @EventHandler
    public void event(GameStatsChangeEvent event) {

        GameStats lastGameStats = event.getCurrentGameStats();
        GameStats newGameStats = event.getNewGameStats();

        if (newGameStats == GameStats.STARTING) {
            // TODO: Decompte
            return;
        }

        if (newGameStats == GameStats.DURING) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (!TeamManager.hasTeam(player)) {
                    TeamManager.setRandomTeam(player);
                }
                String team = TeamManager.getTeam(player);
                player.teleport(Locations.getSpawn(team));
                player.setGameMode(GameMode.SURVIVAL);
                Inventory inv = player.getInventory();
                inv.clear();
                ItemStack sword = new ItemBuilder(Material.IRON_SWORD).enchant(Enchantment.DAMAGE_ALL, 2).build();
                ItemStack gapple = new ItemBuilder(Material.GOLDEN_APPLE).amount(3).build();
                ItemStack pickaxe = new ItemBuilder(Material.IRON_PICKAXE).enchant(Enchantment.DIG_SPEED, 2).meta(itemMeta -> itemMeta.spigot().setUnbreakable(true)).build();
                inv.setItem(0, sword);
                inv.setItem(1, gapple);
                inv.setItem(2, pickaxe);
            }
            return;
        }

        if (newGameStats == GameStats.STOPPING) {
            // TODO: Game restart
            Utils.sendAllLobby();
            Server server = Domination.getInstance().getServer();
            server.unloadWorld(server.getWorld("FK-OASIS"), false);
            World fk = new WorldCreator("FK-OASIS").environment(World.Environment.NORMAL).createWorld();
            fk.setAutoSave(false);
            Domination.getGameInstance().setGameStats(GameStats.WAITING);
        }

    }

}
