package net.hydramc.domination.gui;

import fr.mrmicky.fastinv.FastInv;
import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;

public class TeamSelector extends FastInv {

    public TeamSelector() {
        super(45, ChatColor.GOLD + "Example inventory");

        // Just add a random item
        setItem(22, new ItemStack(Material.IRON_SWORD), e -> e.getWhoClicked().sendMessage("You clicked on the sword"));

        // Add some blocks to the borders
        setItems(getBorders(), new ItemBuilder(Material.LAPIS_BLOCK).name(" ").build());

    }

    @Override
    public void onOpen(InventoryOpenEvent event) {
        event.getPlayer().sendMessage(ChatColor.GOLD + "You opened the inventory");
    }

    @Override
    public void onClose(InventoryCloseEvent event) {
        event.getPlayer().sendMessage(ChatColor.GOLD + "You closed the inventory");
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        // do something
    }
}