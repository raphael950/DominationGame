package net.hydramc.domination.scoreboard;

import fr.mrmicky.fastboard.FastBoard;
import net.hydramc.domination.game.Game;
import net.hydramc.domination.step.Step;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

public class ScoreboardManager {

    private final Game game;
    private final Map<Player, FastBoard> playerBoards;

    public ScoreboardManager(Game game) {
        this.game = game;
        this.playerBoards = new WeakHashMap<Player, FastBoard>();
    }

    public FastBoard getOrCreate(Player player) {
        FastBoard board;

        if (player == null)
            return null;
        board = this.playerBoards.get(player);
        if (board == null) {
            board = new FastBoard(player);
            this.playerBoards.put(player, board);
        }
        return board;
    }

    public boolean remove(Player player) {
        FastBoard fastBoard;

        if (player == null)
            return false;
        fastBoard = this.playerBoards.remove(player);
        if (fastBoard == null)
            return false;
        fastBoard.delete();
        return true;
    }

    public void update() {
        final Step currentStep = game.getStepManager().getCurrentStep();
        FastBoard fastBoard;
        List<String> playerLines;

        for (Player player : Bukkit.getOnlinePlayers()) {
            playerLines = (currentStep != null) ? currentStep.buildScoreboard(player, this.game)
            : ScoreboardBuilder.build(player, this.game);

            if (playerLines == null || playerLines.isEmpty())
                remove(player);
            else {
                fastBoard = getOrCreate(player);
                fastBoard.updateTitle(playerLines.get(0));
                playerLines.remove(0);
                fastBoard.updateLines(playerLines);
            }
        }
    }
}
