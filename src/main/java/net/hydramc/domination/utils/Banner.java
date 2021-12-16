package net.hydramc.domination.utils;

import fr.mrcubee.langlib.Lang;
import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Banner {

    public static ItemStack white(Player player) {
        return new ItemBuilder(Material.BANNER)
                .data(15)
                .name(Lang.getMessage(player, "game.waiting.item.team_selector", "ERROR", true))
                .build();
    }

    public static ItemStack red(Player player) {
        return new ItemBuilder(Material.BANNER)
                .data(1)
                .name(Lang.getMessage(player, "game.waiting.item.team_selector", "ERROR", true))
                .build();
    }

    public static ItemStack blue(Player player) {
        return new ItemBuilder(Material.BANNER)
                .data(4)
                .name(Lang.getMessage(player, "game.waiting.item.team_selector", "ERROR", true))
                .build();
    }

}
