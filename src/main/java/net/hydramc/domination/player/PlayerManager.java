package net.hydramc.domination.player;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import fr.mrcubee.langlib.Lang;
import fr.mrmicky.fastinv.ItemBuilder;
import net.hydramc.domination.Domination;
import net.hydramc.domination.game.Game;
import net.hydramc.domination.team.Team;
import net.hydramc.domination.team.TeamColor;
import net.hydramc.domination.team.TeamManager;
import net.hydramc.domination.utils.Items;
import net.hydramc.domination.utils.Locations;
import net.minecraft.server.v1_8_R3.ChatComponentText;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
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

    public void broadcast(String messageId, Object... objects) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(Lang.getMessage(player, messageId, "ERROR", true, objects));
        }
    }

    public void sendActionBar(Player player, String message) {
        PacketPlayOutChat packet = new PacketPlayOutChat(new ChatComponentText(message), (byte)2);
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
    }

    public void sendActionBar(Player player, String messageId, Object... parameters) {
        String message = Lang.getMessage(player, messageId, "ERROR", true, parameters);
        PacketPlayOutChat packet = new PacketPlayOutChat(new ChatComponentText(message), (byte)2);
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
    }

    public void sendGlobalActionBar(String messageId, Object... parameters) {
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            sendActionBar(player, Lang.getMessage(player, messageId, "ERROR", true, parameters));
        }
    }

    public void spawnWaiting(Player player) {
        player.setGameMode(GameMode.ADVENTURE);
        player.setHealth(20);
        player.setExp(0);
        player.setLevel(0);
        teleportToSpawn(player);
        giveJoinItems(player);
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

    public void clearArmor(Player player) {
        player.getInventory().setHelmet(null);
        player.getInventory().setChestplate(null);
        player.getInventory().setLeggings(null);
        player.getInventory().setBoots(null);
    }

    public void giveJoinItems(Player player) {

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

    public void teleportToSpawn(Player player) {
        Location location = Locations.getLobby();
        player.teleport(location);
    }

    public void sendAllLobby() {
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            spawnWaiting(player);
        }
    }

    public boolean isInArea(Team team, Location location, Game game) {
        if (team == null)
            return false;
        return team.getRegion().isInCircle(location);
    }

    public boolean isInEnemyArea(Team team, Location location, Game game) {
        if (team == null)
            return false;
        Team enemy = game.getTeamManager().getEnemyTeam(team);
        return enemy.getRegion().isInCircle(location);
    }

}
