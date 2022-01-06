package net.hydramc.domination.listeners.game;

import fr.mrcubee.langlib.Lang;
import fr.mrmicky.fastinv.ItemBuilder;
import net.hydramc.GameStats;
import net.hydramc.domination.Domination;
import net.hydramc.domination.event.GameStatsChangeEvent;
import net.hydramc.domination.game.Game;
import net.hydramc.domination.team.NameTag;
import net.hydramc.domination.team.Team;
import net.hydramc.domination.team.TeamColor;
import net.hydramc.domination.team.TeamManager;
import net.hydramc.domination.utils.GameUtils;
import net.hydramc.domination.utils.Locations;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
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

        Game game = Domination.getGameInstance();
        Domination main = Domination.getInstance();
        TeamManager teamManager = game.getTeamManager();

        if (newGameStats == GameStats.DURING) {

            for (Player player : Bukkit.getOnlinePlayers()) {
                Team team = teamManager.getOrCreateTeam(player);
                TeamColor teamColor = team.getTeamColor();

                NameTag.setNameTag(player, teamColor.getColor());
                game.getScoreboardManager().getOrCreate(player);
                game.getPlayerStatsManager().getOrCreatePlayerStats(player);

                player.teleport(Locations.getSpawn(team.getName()));
                player.setGameMode(GameMode.SURVIVAL);
                Inventory inv = player.getInventory();
                inv.clear();
                ItemStack sword = new ItemBuilder(Material.IRON_SWORD).enchant(Enchantment.DAMAGE_ALL, 2).meta(itemMeta -> itemMeta.spigot().setUnbreakable(true)).build();
                ItemStack gapple = new ItemBuilder(Material.GOLDEN_APPLE).amount(3).build();
                ItemStack pickaxe = new ItemBuilder(Material.IRON_PICKAXE).enchant(Enchantment.DIG_SPEED, 2).meta(itemMeta -> itemMeta.spigot().setUnbreakable(true)).build();
                inv.setItem(0, sword);
                inv.setItem(1, gapple);
                inv.setItem(2, pickaxe);
            }

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

        }

        if (newGameStats == GameStats.CLOSING) {
            GameUtils.sendAllLobby();
            Bukkit.getServer().shutdown();
        }

    }

}
