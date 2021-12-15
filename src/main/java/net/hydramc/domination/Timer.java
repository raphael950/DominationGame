package net.hydramc.domination;

import net.hydramc.GameStats;
import org.bukkit.scheduler.BukkitRunnable;

public class Timer extends BukkitRunnable {

    private final Game game;

    protected Timer(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        final GameStats gameStats = game.getGameStats();

        if (gameStats == GameStats.STOPPING) {
            cancel();
            return;
        }
        // TODO: Insert timer code here.
    }
}
