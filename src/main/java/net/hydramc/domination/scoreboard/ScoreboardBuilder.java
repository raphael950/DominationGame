package net.hydramc.domination.scoreboard;

import fr.mrmicky.fastboard.FastBoard;
import net.hydramc.domination.game.Game;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;


public class ScoreboardBuilder {


    private static final Map<Player, FastBoard> boards = new HashMap<>();

    private static boolean isSetup(Player player) {
        return boards.containsKey(player);
    }

    private static FastBoard createBoard(Player player) {
        FastBoard board = new FastBoard(player);
        boards.put(player, board);
        return board;
    }

    private static FastBoard getBoard(Player player) {
        if (!isSetup(player)) {
            return createBoard(player);
        }
        return boards.get(player);
    }

    public static void deleteBoard(Player player) {
        boards.remove(player);
    }

    public static void build(Player player, Game game) {
        FastBoard board = getBoard(player);
        board.updateTitle("§3Domination");

        switch (game.getGameStats()) {

            case WAITING:
                board.updateLines(
                        "",
                        "§7Carte: §6Oasis",
                        "§7Joueurs: §f" + Bukkit.getOnlinePlayers().size(),
                        "",
                        "§eplay.hydramc.net"
                );
                break;

            case DURING:
                // TODO: In game scoreboard
                board.updateLines(
                        "",
                        "§7Jour: §b1",
                        "",
                        "§cRouge: " + "§f500",
                        "§9Bleu: " + "§f500",
                        "",
                        "§eplay.hydramc.net"
                );
                break;
        }
    }

}
