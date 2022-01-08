package net.hydramc.domination.game;

import fr.mrcubee.annotation.spigot.config.ConfigAnnotation;
import fr.mrcubee.finder.plugin.PluginFinder;
import fr.mrcubee.weak.WeakHashSet;
import net.hydramc.GameStats;
import net.hydramc.domination.event.GameStatsChangeEvent;
import net.hydramc.domination.player.PlayerManager;
import net.hydramc.domination.player.PlayerStatsManager;
import net.hydramc.domination.scoreboard.ScoreboardManager;
import net.hydramc.domination.step.StepManager;
import net.hydramc.domination.team.TeamManager;
import net.hydramc.domination.utils.Config;
import net.hydramc.domination.utils.TitleManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Set;

public class Game {

    private final GameSetting gameSetting;
    private final StepManager stepManager;
    private final PlayerStatsManager playerStatsManager;
    private final PlayerManager playerManager;
    private final ScoreboardManager scoreboardManager;
    private final Timer timer;
    private final Set<Player> players;
    private final Config locationConfig;
    private final TeamManager teamManager;
    private final TitleManager titleManager;
    private GameStats gameStats;

    public Game() {
        final Plugin plugin = (Plugin) PluginFinder.INSTANCE.findPluginCaller();

        this.gameStats = GameStats.OPENING;
        this.locationConfig = new Config("locations.yml", plugin);
        this.gameSetting = new GameSetting();
        this.stepManager = new StepManager(this);
        this.playerStatsManager = new PlayerStatsManager();
        this.playerManager = new PlayerManager(this);
        this.scoreboardManager = new ScoreboardManager(this);
        this.timer = new Timer(this);
        this.players = new WeakHashSet<>();
        this.teamManager = new TeamManager(this);
        this.titleManager = new TitleManager();

        ConfigAnnotation.loadClass(plugin.getConfig(), gameSetting);
        this.timer.runTaskTimer(plugin, 0L, 10L);
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

    public PlayerStatsManager getPlayerStatsManager() {
        return this.playerStatsManager;
    }

    public PlayerManager getPlayerManager() {
        return this.playerManager;
    }

    public ScoreboardManager getScoreboardManager() {
        return this.scoreboardManager;
    }

    public TeamManager getTeamManager() {
        return this.teamManager;
    }

    public TitleManager getTitleManager() {
        return titleManager;
    }
}