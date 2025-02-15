package net.hydramc.domination.gui;

import fr.mrcubee.langlib.Lang;
import fr.mrmicky.fastinv.FastInv;
import fr.mrmicky.fastinv.ItemBuilder;
import net.hydramc.domination.Domination;
import net.hydramc.domination.game.Game;
import net.hydramc.domination.player.PlayerManager;
import net.hydramc.domination.team.NameTagManager;
import net.hydramc.domination.team.TeamManager;
import net.hydramc.domination.utils.Items;
import net.hydramc.domination.utils.Cooldown;
import net.hydramc.domination.utils.Head;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class TeamSelector extends FastInv {

    private final Game game = Domination.getGameInstance();
    private final PlayerManager playerManager = game.getPlayerManager();
    private final TeamManager teamManager = game.getTeamManager();
    private final NameTagManager nameTagManager = game.getNameTagManager();

    public TeamSelector(Player player) {

        super(9, Lang.getMessage("game.waiting.gui.title", "ERROR", true));

        // random team item
        ItemStack head = Head.getCustomTextureHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzk3OTU1NDYyZTRlNTc2NjY0NDk5YWM0YTFjNTcyZjYxNDNmMTlhZDJkNjE5NDc3NjE5OGY4ZDEzNmZkYjIifX19");
        head = new ItemBuilder(head).name(Lang.getMessage("game.waiting.gui.random", "ERROR", true)).build();
        setItem(4, head, e -> {
            if (cooldown(e)) {
                player.sendMessage(Lang.getMessage("game.waiting.gui.random.click", "ERROR", true));
                player.getInventory().setItem(0, Items.whiteBanner(player));
                teamManager.removeTeam(player);
                nameTagManager.setWaitingNameTag(player, "&7");
            }

        });


        // blue team item
        ItemStack blueFlag = new ItemBuilder(Material.BANNER).data(4)
                .name(Lang.getMessage("game.waiting.gui.blue", "ERROR", true))
                .lore("§7Membres de l'équipe:")
                .lore("")
                .build();
        setItem(2, blueFlag, e -> {
            if (cooldown(e)) {
                player.sendMessage(Lang.getMessage("game.waiting.gui.blue.click", "ERROR", true));
                player.getInventory().setItem(0, Items.blueBanner(player));
                teamManager.setTeam(player, "blue");
                nameTagManager.setWaitingNameTag(player, "&9");

            }

        });

        // red team item
        ItemStack redFlag = new ItemBuilder(Material.BANNER).data(1)
                .name(Lang.getMessage("game.waiting.gui.red", "ERROR", true))
                .build();
        setItem(6, redFlag, e -> {
            if (cooldown(e)) {
                player.sendMessage(Lang.getMessage("game.waiting.gui.red.click", "ERROR", true));
                player.getInventory().setItem(0, Items.redBanner(player));
                teamManager.setTeam(player, "red");
                nameTagManager.setWaitingNameTag(player, "&c");
            }
        });

        // setItems(getBorders(), new ItemBuilder(Material.STAINED_GLASS_PANE).data(3).build());

    }

    private boolean cooldown(InventoryClickEvent e) {

        Player player = (Player)e.getWhoClicked();
        if (Cooldown.hasCooldown(player)) {
            player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
            playerManager.sendActionBar(player, Lang.getMessage("gui.cooldown", "ERROR", true));
            return false;
        }
        Cooldown.setCooldown(player, 1);
        return true;
    }

}