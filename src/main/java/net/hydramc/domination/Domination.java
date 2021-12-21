package net.hydramc.domination;

import fr.mrcubee.finder.plugin.PluginFinder;
import fr.mrcubee.langlib.Lang;
import fr.mrmicky.fastinv.FastInvManager;
import net.hydramc.GameStats;
import net.hydramc.domination.commands.Dm;
import net.hydramc.domination.commands.Spawn;
import net.hydramc.domination.game.Game;
import net.hydramc.domination.listeners.RegisterListeners;
import net.hydramc.domination.utils.Cooldown;
import net.hydramc.domination.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Domination extends JavaPlugin {

    private Game game;

    @Override
    public void onEnable() {

        this.game = new Game();
        this.game.setGameStats(GameStats.OPENING);

        Cooldown.setupCooldown();

        Lang.setDefaultLang("FR_fr");

        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        FastInvManager.register(this);
        RegisterListeners.register();

        this.getCommand("spawn").setExecutor(new Spawn());
        this.getCommand("dm").setExecutor(new Dm());

        getLogger().info("On");
        this.game.setGameStats(GameStats.WAITING);

        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            Utils.spawn(player.getPlayer());
        }

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
