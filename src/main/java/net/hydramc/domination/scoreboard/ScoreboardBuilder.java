package net.hydramc.domination.scoreboard;

import fr.mrmicky.fastboard.FastBoard;
import net.hydramc.domination.Game;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;


public class ScoreboardBuilder {


    private static final Map<Player, FastBoard> boards = new HashMap<>();

    private static boolean isSetup(Player player) {
        return boards.containsKey(player);
    }

    private static FastBoard getBoard(Player player) {
        if (!isSetup(player)) {
            return new FastBoard(player);
        }
        return boards.get(player);
    }

    public static void deleteBoard(Player player) {
        boards.remove(player);
    }

    public static void build(Player player, Game game) {
        FastBoard board = getBoard(player);
        board.updateTitle("§3Domination");
        board.updateLines(
                "",
                "§7Joueurs: §f" + Bukkit.getOnlinePlayers().size(),
                "",
                "§eplay.hydramc.net"
        );
    }

}
