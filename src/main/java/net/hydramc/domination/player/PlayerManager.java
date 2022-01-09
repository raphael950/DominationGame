package net.hydramc.domination.player;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import fr.mrmicky.fastinv.ItemBuilder;
import net.hydramc.domination.Domination;
import net.hydramc.domination.game.Game;
import net.hydramc.domination.team.Team;
import net.hydramc.domination.team.TeamColor;
import net.hydramc.domination.team.TeamManager;
import net.hydramc.domination.utils.Locations;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PlayerManager {

    private final Game game;
    private final TeamManager teamManager;

    public PlayerManager(Game game, TeamManager teamManager) {
        this.game = game;
        this.teamManager = teamManager;
    }

    public void setupPlayers() {
        for (Player player : game.getPlayers()) {
            PlayerData playerData = game.getPlayerStatsManager().getOrCreatePlayerStats(player);
            playerData.setDead(true);

            Team team = teamManager.getOrGiveTeam(player);
            TeamColor teamColor = team.getTeamColor();

            game.getNameTagManager().setNameTag(player, teamColor.getColor());
            game.getScoreboardManager().getOrCreate(player);

            player.teleport(Locations.getSpawn(team.getName()));
            player.setGameMode(GameMode.SURVIVAL);

            // TODO: Kit system

            Inventory inv = player.getInventory();
            inv.clear();
            ItemStack sword = new ItemBuilder(Material.IRON_SWORD).enchant(Enchantment.DAMAGE_ALL, 2).meta(itemMeta -> itemMeta.spigot().setUnbreakable(true)).build();
            ItemStack gapple = new ItemBuilder(Material.GOLDEN_APPLE).amount(3).build();
            ItemStack pickaxe = new ItemBuilder(Material.IRON_PICKAXE).enchant(Enchantment.DIG_SPEED, 2).meta(itemMeta -> itemMeta.spigot().setUnbreakable(true)).build();
            inv.setItem(0, sword);
            inv.setItem(1, gapple);
            inv.setItem(2, pickaxe);

            playerData.setDead(false);
        }
    }

    public void sendToLobby(Player player) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF("lobby");
        player.sendPluginMessage(Domination.getInstance(), "BungeeCord", out.toByteArray());
    }

}
