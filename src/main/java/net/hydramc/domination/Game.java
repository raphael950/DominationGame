package net.hydramc.domination;

import fr.mrcubee.annotation.spigot.config.ConfigAnnotation;
import fr.mrcubee.finder.plugin.PluginFinder;
import net.hydramc.GameStats;
import net.hydramc.domination.event.GameStatsChangeEvent;
import net.hydramc.domination.scoreboard.ScoreboardManager;
import net.hydramc.domination.step.StepManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class Game {

    private final GameSetting gameSetting;
    private final ScoreboardManager scoreboardManager;
    private final StepManager stepManager;
    private final Timer timer;
    private GameStats gameStats;

    public Game() {
        final Plugin plugin = (Plugin) PluginFinder.INSTANCE.findPluginCaller();

        this.gameStats = GameStats.OPENING;
        this.gameSetting = new GameSetting();
        this.scoreboardManager = new ScoreboardManager();
        this.stepManager = new StepManager(this);
        this.timer = new Timer(this);
        if (plugin != null) {
            ConfigAnnotation.loadClass(plugin.getConfig(), gameSetting);
            this.timer.runTaskTimer(plugin, 0L, 10L);
        }
    }

    public GameSetting getGameSetting() {
        return this.gameSetting;
    }

    public ScoreboardManager getScoreboardManager() {
        return this.scoreboardManager;
    }

    public StepManager getStepManager() {
        return this.stepManager;
    }

    public void setGameStats(GameStats newStats) {
        if (newStats == null || this.gameStats == newStats
        || (this.gameStats.ordinal() >= 2 && this.gameStats.ordinal() > newStats.ordinal()))
            return;
        Bukkit.getPluginManager().callEvent(new GameStatsChangeEvent(newStats));
        this.gameStats = newStats;
    }

    public GameStats getGameStats() {
        return this.gameStats;
    }

}