package net.hydramc.domination;

import fr.mrcubee.finder.plugin.PluginFinder;
import net.hydramc.GameStats;
import net.hydramc.domination.listeners.RegisterListeners;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Domination extends JavaPlugin {

    private Game game;

    @Override
    public void onEnable() {
        game = new Game();
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        RegisterListeners.register();
        getLogger().info("On");
    }

    @Override
    public void onDisable() {
        this.game.setGameStats(GameStats.CLOSING);
        getLogger().info("Off");
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

    public static Game getGameInstance() {
        final Domination dominationPlugin = getInstance();

        if (dominationPlugin == null)
            return null;
        return dominationPlugin.getGame();
    }
}
