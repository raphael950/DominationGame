package net.hydramc.domination.scoreboard;

import net.hydramc.domination.game.Game;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;

public class ScoreboardBuilder {

    public static List<String> build(Player player, Game game) {
        List<String> lines = new ArrayList<>();

        lines.add("§3§lDomination");

        if (game.getGameStats().ordinal() > 2) {
            lines.addAll(Arrays.asList(
                    "",
                    "§7Jour: §b1",
                    "",
                    "§cRouge: " + "§f500",
                    "§9Bleu: " + "§f500",
                    ""
            ));
        } else {
            lines.addAll(Arrays.asList(
                    "",
                    "§7Carte: §6Oasis",
                    "§7Joueurs: §f" + Bukkit.getOnlinePlayers().size(),
                    ""
            ));
        }

        // TODO: Create scoreboard's sidebar line for a specific player.
        lines.add("§eplay.hydramc.net");
        return lines;
    }
}
