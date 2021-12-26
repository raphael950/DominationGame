package net.hydramc.domination.game;

import net.hydramc.GameStats;
import net.hydramc.domination.scoreboard.ScoreboardManager;
import net.hydramc.domination.step.Step;
import net.hydramc.domination.step.StepManager;
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
        final StepManager stepManager = this.game.getStepManager();
        final ScoreboardManager scoreboardManager = this.game.getScoreboardManager();
        Step step;

        if (gameStats == GameStats.CLOSING) {
            cancel();
            return;
        }

        if (gameStats.ordinal() < 2 || gameStats.ordinal() > 3) {
            scoreboardManager.update();
            return;
        }

        // Get the currently engaged step.
        step = stepManager.getCurrentStep();

        /*
        If a step is not engaged or if it is completed then the next step will be engaged.

        The step is considered to have no precise duration
        if the value returned by step.getEndTimeMillis() is equal to or less than -1.
         */
        if (step == null || step.getEndTimeMillis() == 0)
            step = stepManager.nextStep();
        if (step != null) {
            step.update();
            for (Player player : this.game.getPlayers())
                step.buildScoreboard(player, this.game);
        }
        // TODO: Update kit's effects.
    }

}
