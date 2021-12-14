package net.hydramc.domination;

import fr.mrcubee.annotation.spigot.config.ConfigAnnotation;
import fr.mrcubee.finder.plugin.PluginFinder;
import net.hydramc.GameStats;
import net.hydramc.event.GameStatsChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class Game {

    private final GameSetting gameSetting;
    private GameStats gameStats;

    public Game() {
        final Plugin plugin = (Plugin) PluginFinder.INSTANCE.findPluginCaller();

        this.gameSetting = new GameSetting();
        if (plugin != null)
            ConfigAnnotation.loadClass(plugin.getConfig(), gameSetting);
        this.gameStats = GameStats.OPENING;
    }

    public GameSetting getGameSetting() {
        return this.gameSetting;
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
