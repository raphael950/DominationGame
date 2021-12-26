package net.hydramc.domination.game;

import fr.mrcubee.annotation.spigot.config.ConfigAnnotation;
import fr.mrcubee.finder.plugin.PluginFinder;
import fr.mrcubee.weak.WeakHashSet;
import net.hydramc.GameStats;
import net.hydramc.domination.event.GameStatsChangeEvent;
import net.hydramc.domination.scoreboard.ScoreboardManager;
import net.hydramc.domination.step.StepManager;
import net.hydramc.domination.team.Team;
import net.hydramc.domination.team.TeamColor;
import net.hydramc.domination.utils.Config;
import net.hydramc.domination.team.Region;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Set;

public class Game {

    private final GameSetting gameSetting;
    private final StepManager stepManager;
    private final Timer timer;
    private final Set<Player> players;
    private final Config locationConfig;
    private GameStats gameStats;
    private final Region redRegion;
    private final Region blueRegion;
    private final Team red;
    private final Team blue;
    private final Team random;
    private final ScoreboardManager scoreboardManager;

    public Game() {
        final Plugin plugin = (Plugin) PluginFinder.INSTANCE.findPluginCaller();

        this.gameStats = GameStats.OPENING;
        this.locationConfig = new Config("locations.yml", plugin);
        this.gameSetting = new GameSetting();
        this.stepManager = new StepManager(this);
        this.timer = new Timer(this);
        this.players = new WeakHashSet<Player>();

        this.scoreboardManager = new ScoreboardManager();

        this.red = new Team("red", new TeamColor("Rouge", "§c", "&l▲§r"));
        this.blue = new Team("blue", new TeamColor("Bleue", "§9", "■"));
        this.random = new Team("random", new TeamColor("", "§7", ""));

        this.redRegion = new Region((Location) locationConfig.getConfig().get("center-red"), 30);
        this.blueRegion = new Region((Location) locationConfig.getConfig().get("center-blue"), 30);

        ConfigAnnotation.loadClass(plugin.getConfig(), gameSetting);
        this.timer.runTaskTimer(plugin, 0L, 10L);
    }

    public Region getRedRegion() {
        return this.redRegion;
    }

    public Region getBlueRegion() {
        return this.blueRegion;
    }

    public Team getRed() {
        return red;
    }

    public Team getBlue() {
        return blue;
    }

    public Team getRandom() {
        return random;
    }

    public GameSetting getGameSetting() {
        return this.gameSetting;
    }

    public Config getLocationConfig() {
        return this.locationConfig;
    }

    public StepManager getStepManager() {
        return this.stepManager;
    }

    public boolean setGameStats(GameStats newStats) {
        if (newStats == null || this.gameStats == newStats
                || (this.gameStats.ordinal() >= 2 && this.gameStats.ordinal() > newStats.ordinal()))
            return false;
        Bukkit.getPluginManager().callEvent(new GameStatsChangeEvent(newStats));
        this.gameStats = newStats;
        return true;
    }

    public GameStats getGameStats() {
        return this.gameStats;
    }

    public Set<Player> getPlayers() {
        return this.players;
    }

    public ScoreboardManager getScoreboardManager() {
        return this.scoreboardManager;
    }
}