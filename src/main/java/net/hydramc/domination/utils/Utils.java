package net.hydramc.domination.utils;

import fr.mrcubee.langlib.Lang;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Utils {

    public static void giveJoinItems(Player player) {
        Inventory inventory = player.getInventory();

        ItemStack teamSelector = new ItemBuilder(Material.BANNER)
                .setName(Lang.getMessage(player, "game.waiting.item.team_selector", "ERROR", true))
                .toItemStack();
        ItemStack lobbyItem = new ItemBuilder(Material.BED)
                .setName(Lang.getMessage(player, "game.waiting.item.lobby_item", "ERROR", true))
                .toItemStack();

        inventory.setItem(0, teamSelector);
        inventory.setItem(8, lobbyItem);
    }

    public static void teleportToSpawn(Player player) {
        Location loc = player.getLocation().getWorld().getSpawnLocation();
        player.teleport(loc);
    }

}
