package net.hydramc.domination.game;

import fr.mrcubee.annotation.spigot.config.ConfigAnnotation;
import fr.mrcubee.finder.plugin.PluginFinder;
import fr.mrcubee.weak.WeakHashSet;
import net.hydramc.GameStats;
import net.hydramc.domination.event.GameStatsChangeEvent;
import net.hydramc.domination.scoreboard.ScoreboardManager;
import net.hydramc.domination.step.StepManager;
import net.hydramc.domination.utils.Config;
import net.hydramc.domination.utils.Region;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Set;

public class Game {

    private final GameSetting gameSetting;
    private final ScoreboardManager scoreboardManager;
    private final StepManager stepManager;
    private final Timer timer;
    private final Set<Player> players;
    private final Config locationConfig;
    private GameStats gameStats;
    private final Region redRegion;
    private final Region blueRegion;

    public Game() {
        final Plugin plugin = (Plugin) PluginFinder.INSTANCE.findPluginCaller();

        this.gameStats = GameStats.OPENING;
        this.locationConfig = new Config("locations.yml", plugin);
        this.gameSetting = new GameSetting();
        this.scoreboardManager = new ScoreboardManager();
        this.stepManager = new StepManager(this);
        this.timer = new Timer(this);
        this.players = new WeakHashSet<Player>();

        this.redRegion = new Region((Location) locationConfig.getConfig().get("spawn-red"), 30);
        this.blueRegion = new Region((Location) locationConfig.getConfig().get("spawn-blue"), 30);

        if (plugin != null) {
            ConfigAnnotation.loadClass(plugin.getConfig(), gameSetting);
            this.timer.runTaskTimer(plugin, 0L, 10L);
        }
    }

    public Region getRedRegion() {
        return this.redRegion;
    }

    public Region getBlueRegion() {
        return this.blueRegion;
    }

    public GameSetting getGameSetting() {
        return this.gameSetting;
    }

    public Config getLocationConfig() {
        return this.locationConfig;
    }

    public ScoreboardManager getScoreboardManager() {
        return this.scoreboardManager;
    }

    public StepManager getStepManager() {
        return this.stepManager;
    }

    public boolean setGameStats(GameStats newStats) {
        if (newStats == null || this.gameStats == newStats)
            return false;
        this.gameStats = newStats;
        Bukkit.getPluginManager().callEvent(new GameStatsChangeEvent(newStats));
        return true;
    }

    public GameStats getGameStats() {
        return this.gameStats;
    }

    public Set<Player> getPlayers() {
        return this.players;
    }

}