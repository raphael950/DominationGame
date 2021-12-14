package net.hydramc.domination;

import fr.mrcubee.annotation.spigot.config.ConfigAnnotation;
import fr.mrcubee.finder.plugin.PluginFinder;
import org.bukkit.plugin.Plugin;

public class Game {

    private final GameSetting gameSetting;

    public Game() {
        final Plugin plugin = (Plugin) PluginFinder.INSTANCE.findPluginCaller();

        this.gameSetting = new GameSetting();
        if (plugin != null)
            ConfigAnnotation.loadClass(plugin.getConfig(), gameSetting);
    }

    public GameSetting getGameSetting() {
        return this.gameSetting;
    }
}
