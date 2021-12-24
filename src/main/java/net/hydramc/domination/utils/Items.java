package net.hydramc.domination.utils;

import fr.mrcubee.langlib.Lang;
import fr.mrmicky.fastinv.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Items {

    public static ItemStack whiteBanner(Player player) {
        return new ItemBuilder(Material.BANNER)
                .data(15)
                .name(Lang.getMessage(player, "game.waiting.item.team_selector", "ERROR", true))
                .build();
    }

    public static ItemStack redBanner(Player player) {
        return new ItemBuilder(Material.BANNER)
                .data(1)
                .name(Lang.getMessage(player, "game.waiting.item.team_selector", "ERROR", true))
                .build();
    }

    public static ItemStack blueBanner(Player player) {
        return new ItemBuilder(Material.BANNER)
                .data(4)
                .name(Lang.getMessage(player, "game.waiting.item.team_selector", "ERROR", true))
                .build();
    }

}
