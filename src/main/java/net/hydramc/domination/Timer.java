package net.hydramc.domination;

import net.hydramc.GameStats;
import net.hydramc.domination.scoreboard.ScoreboardManager;
import org.bukkit.scheduler.BukkitRunnable;

public class Timer extends BukkitRunnable {

    private final Game game;

    protected Timer(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        final GameStats gameStats = game.getGameStats();
        final ScoreboardManager scoreboardManager = game.getScoreboardManager();

        if (gameStats == GameStats.CLOSING) {
            cancel();
            return;
        }
        scoreboardManager.updateAllPlayers();
        if (gameStats.ordinal() < 2 || gameStats.ordinal() > 3)
            return;
        // TODO: Update kit's effects.
    }

}
