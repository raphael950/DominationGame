package net.hydramc.domination.utils;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import fr.mrcubee.langlib.Lang;
import fr.mrmicky.fastinv.ItemBuilder;
import net.hydramc.domination.Domination;
import net.hydramc.domination.game.Game;
import net.hydramc.domination.player.PlayerData;
import net.hydramc.domination.team.Team;
import net.hydramc.domination.team.TeamManager;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.atomic.AtomicInteger;

public class GameUtils {

    private static final Game game = Domination.getGameInstance();

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

        Team team = TeamManager.getTeam(player);

        player.setGameMode(GameMode.SPECTATOR);

        PlayerData playerData = PlayerData.get(player);

        playerData.setDead(true);

        AtomicInteger seconds = new AtomicInteger(5);
        Domination.getInstance().getServer().getScheduler().runTaskTimer(Domination.getInstance(), () -> {

            ActionBar.sendPlayerActionBar(player, Lang.getMessage("game.during.dead_action_bar", "ERROR", true, seconds));
            seconds.addAndGet(-1);

        }, 20L, 100L);

        player.teleport(Locations.getSpawn(team.getName()));
        playerData.setDead(false);

        player.playSound(player.getLocation(), Sound.LEVEL_UP, 1, 1);


    }

    public static Team reverseTeam(Team team) {
        Team red = game.getRed();
        Team blue = game.getBlue();

        if (team == red)
            return blue;
        return red;
    }


    public static void sendAllLobby() {
        Team random = game.getRandom();
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            GameUtils.spawn(player);
            TeamManager.waitingTeam(player, random, true);
        }
    }

    public static boolean isInArea(Team team, Location location, Game game) {
        if (team == null)
            return false;
        if (team.getName().equals("red")) {
            return game.getRedRegion().isInCircle(location);
        }
        return game.getBlueRegion().isInCircle(location);
    }

    public static boolean isInEnemyArea(Team team, Location location, Game game) {
        if (team == null)
            return false;
        if (team.getName().equals("red")) {
            return game.getBlueRegion().isInCircle(location);
        }
        return game.getRedRegion().isInCircle(location);
    }


}
