package net.hydramc.domination.utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Utils {

    public static void giveJoinItems(Player player) {
        Inventory inventory = player.getInventory();

        ItemStack teamSelector = new ItemBuilder(Material.BANNER).setName("§aEquipe &8| &7Clic droit").toItemStack();
        ItemStack lobbyItem = new ItemBuilder(Material.BED).setName("§cQuitter &8| &7Clic droit").toItemStack();

        inventory.setItem(0, teamSelector);
        inventory.setItem(8, lobbyItem);
    }

    public static void teleportToSpawn(Player player) {
        Location loc = player.getLocation().getWorld().getSpawnLocation();
        player.teleport(loc);
    }

}
