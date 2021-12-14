package net.hydramc.domination;

import fr.mrcubee.finder.plugin.PluginFinder;
import net.hydramc.GameStats;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Domination extends JavaPlugin {

    private Game game;

    @Override
    public void onEnable() {
        getLogger().info("[Domination] On");
        game = new Game();
    }

    @Override
    public void onDisable() {
        this.game.setGameStats(GameStats.CLOSING);
        getLogger().info("[Domination] Off");
    }

    public Game getGame() {
        return this.game;
    }

    public static Domination getInstance() {
        final Plugin plugin = (Plugin) PluginFinder.INSTANCE.findPlugin();

        if (!(plugin instanceof Domination))
            return null;
        return (Domination) plugin;
    }
}
