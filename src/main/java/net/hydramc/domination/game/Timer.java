package net.hydramc.domination.game;

import net.hydramc.GameStats;
import net.hydramc.domination.scoreboard.ScoreboardManager;
import net.hydramc.domination.step.Step;
import net.hydramc.domination.step.StepManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Timer extends BukkitRunnable {

    private final Game game;

    public Timer(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        final GameStats gameStats = this.game.getGameStats();
        final ScoreboardManager scoreboardManager = this.game.getScoreboardManager();
        final StepManager stepManager = this.game.getStepManager();
        Step step;

        if (gameStats == GameStats.CLOSING) {
            cancel();
            return;
        }
        if (gameStats.ordinal() < 2 || gameStats.ordinal() > 3) {
            scoreboardManager.updateAllPlayers();
            return;
        }
        step = stepManager.getCurrentStep();
        if (step == null || step.getEndTimeMillis() == 0)
            step = stepManager.nextStep();
        if (step != null) {
            step.update();
            for (Player player : Bukkit.getOnlinePlayers())
                step.updateScoreboard(player, this.game);
        }
        // TODO: Update kit's effects.
    }

}
