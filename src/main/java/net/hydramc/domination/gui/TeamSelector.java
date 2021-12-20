package net.hydramc.domination.gui;

import fr.mrcubee.langlib.Lang;
import fr.mrmicky.fastinv.FastInv;
import fr.mrmicky.fastinv.ItemBuilder;
import net.hydramc.domination.team.TeamManager;
import net.hydramc.domination.utils.ActionBar;
import net.hydramc.domination.utils.Banner;
import net.hydramc.domination.utils.Cooldown;
import net.hydramc.domination.utils.Head;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class TeamSelector extends FastInv {

    public TeamSelector(Player player) {

        super(9, Lang.getMessage("game.waiting.gui.title", "ERROR", true));

        // random team item
        ItemStack head = Head.getCustomTextureHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzk3OTU1NDYyZTRlNTc2NjY0NDk5YWM0YTFjNTcyZjYxNDNmMTlhZDJkNjE5NDc3NjE5OGY4ZDEzNmZkYjIifX19");
        head = new ItemBuilder(head).name(Lang.getMessage("game.waiting.gui.random", "ERROR", true)).build();
        setItem(4, head, e -> {
            if (cooldown(e)) {
                player.sendMessage(Lang.getMessage("game.waiting.gui.random.click", "ERROR", true));
                player.getInventory().setItem(0, Banner.white(player));

                if (TeamManager.hasTeam(player)) {
                    TeamManager.removeTeam(player);
                }
            }

        });


        // blue team item
        ItemStack blueFlag = new ItemBuilder(Material.BANNER).data(4)
                .name(Lang.getMessage("game.waiting.gui.blue", "ERROR", true))
                .build();
        setItem(2, blueFlag, e -> {
            if (cooldown(e)) {
                player.sendMessage(Lang.getMessage("game.waiting.gui.blue.click", "ERROR", true));
                player.getInventory().setItem(0, Banner.blue(player));
                TeamManager.setTeam(player, "blue");
                player.sendMessage(TeamManager.getTeam(player));
            }

        });

        // red team item
        ItemStack redFlag = new ItemBuilder(Material.BANNER).data(1)
                .name(Lang.getMessage("game.waiting.gui.red", "ERROR", true))
                .build();
        setItem(6, redFlag, e -> {
            if (cooldown(e)) {
                player.sendMessage(Lang.getMessage("game.waiting.gui.red.click", "ERROR", true));
                player.getInventory().setItem(0, Banner.red(player));
                TeamManager.setTeam(player, "red");
                player.sendMessage(TeamManager.getTeam(player));
            }
        });

    }

    private boolean cooldown(InventoryClickEvent e) {

        Player player = (Player)e.getWhoClicked();
        if (Cooldown.hasCooldown(player)) {
            player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
            ActionBar.sendPlayerActionBar(player, Lang.getMessage("gui.cooldown", "ERROR", true));
            return false;
        }
        Cooldown.setCooldown(player, 1);
        return true;
    }

}