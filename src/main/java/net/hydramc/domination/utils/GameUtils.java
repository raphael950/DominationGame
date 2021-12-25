package net.hydramc.domination.utils;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import fr.mrcubee.langlib.Lang;
import fr.mrmicky.fastinv.ItemBuilder;
import net.hydramc.domination.Domination;
import net.hydramc.domination.game.Game;
import net.hydramc.domination.team.Region;
import net.hydramc.domination.team.Team;
import net.hydramc.domination.team.TeamManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GameUtils {

    public static void spawn(Player player) {
        player.setGameMode(GameMode.ADVENTURE);
        player.setHealth(20);
        player.setExp(0);
        player.setLevel(0);
        teleportToSpawn(player);
        giveJoinItems(player);
    }

    public static void clearArmor(Player player) {
        player.getInventory().setHelmet(null);
        player.getInventory().setChestplate(null);
        player.getInventory().setLeggings(null);
        player.getInventory().setBoots(null);
    }

    public static void giveJoinItems(Player player) {

        player.getInventory().setHeldItemSlot(0);
        clearArmor(player);

        Inventory inventory = player.getInventory();
        inventory.clear();

        ItemStack teamSelector = Items.whiteBanner(player);
        ItemStack lobbyItem = new ItemBuilder(Material.BED)
                .name(Lang.getMessage(player, "game.waiting.item.lobby_item", "ERROR", true))
                .build();

        inventory.setItem(0, teamSelector);
        inventory.setItem(8, lobbyItem);
    }

    public static void teleportToSpawn(Player player) {
        Location location = Locations.getLobby();
        player.teleport(location);
    }

    public static void sendToLobby(Player player) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF("lobby");
        player.sendPluginMessage(Domination.getInstance(), "BungeeCord", out.toByteArray());
    }

    public static void death(Player player, Entity... attacker) {
        player.setGameMode(GameMode.SPECTATOR);
        // TODO: Death system

    }

    public static String reverseTeam(String team) {
        if (team.equals("red"))
            return "blue";
        return "red";
    }


    public static void sendAllLobby() {
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            GameUtils.spawn(player);
            TeamManager.setRandom(player);
        }
    }

    public static boolean isEnemyArea(Team team, Location location, Game game) {
        if (team.getName().equals("red")) {
            return game.getBlueRegion().isInCircle(location);
        }
        return game.getRedRegion().isInCircle(location);
    }

}
