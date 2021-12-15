package net.hydramc.domination.gui;

import fr.mrcubee.langlib.Lang;
import fr.mrmicky.fastinv.FastInv;
import fr.mrmicky.fastinv.ItemBuilder;
import net.hydramc.domination.utils.Head;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;

public class TeamSelector extends FastInv {

    public TeamSelector() {

        super(8, Lang.getMessage("game.waiting.gui.title", "ERROR", true));

        // random team item
        ItemStack head = Head.getCustomTextureHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzk3OTU1NDYyZTRlNTc2NjY0NDk5YWM0YTFjNTcyZjYxNDNmMTlhZDJkNjE5NDc3NjE5OGY4ZDEzNmZkYjIifX19");
        head = new ItemBuilder(head).name(Lang.getMessage("game.waiting.gui.random", "ERROR", true)).build();
        setItem(4, head, e -> e.getWhoClicked()
                .sendMessage(Lang.getMessage("game.waiting.gui.random.click", "ERROR", true)));


        // blue team item
        ItemStack blueFlag = new ItemBuilder(Material.BANNER).data(11)
                .name(Lang.getMessage("game.waiting.gui.blue", "ERROR", true))
                .build();
        setItem(2, blueFlag, e -> e.getWhoClicked()
                .sendMessage(Lang.getMessage("game.waiting.gui.blue.click", "ERROR", true)));

        // red team item
        ItemStack redFlag = new ItemBuilder(Material.BANNER).data(14)
                .name(Lang.getMessage("game.waiting.gui.red", "ERROR", true))
                .build();
        setItem(6, redFlag, e -> e.getWhoClicked()
                .sendMessage(Lang.getMessage("game.waiting.gui.red.click", "ERROR", true)));

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