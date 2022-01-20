package net.hydramc.domination;

import fr.mrcubee.finder.plugin.PluginFinder;
import fr.mrcubee.langlib.Lang;
import fr.mrmicky.fastinv.FastInvManager;
import net.hydramc.GameStats;
import net.hydramc.domination.commands.DominationCommand;
import net.hydramc.domination.commands.SpawnCommand;
import net.hydramc.domination.entity.v1_8_R3.CustomEntityType;
import net.hydramc.domination.game.Game;
import net.hydramc.domination.listeners.RegisterListeners;
import net.hydramc.domination.utils.Cooldown;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Domination extends JavaPlugin {

    private Game game;

    @Override
    public void onEnable() {

        this.getServer().unloadWorld(this.getServer().getWorld("FK-OASIS"), false);
        World fk = new WorldCreator("FK-OASIS").environment(World.Environment.NORMAL).createWorld();
        fk.setAutoSave(false);

        CustomEntityType.registerAllEntities();

        this.game = new Game();
        this.game.setGameStats(GameStats.OPENING);

        Cooldown.setupCooldown();

        Lang.setDefaultLang("FR_fr");

        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        FastInvManager.register(this);
        RegisterListeners.register();

        this.getCommand("spawn").setExecutor(new SpawnCommand());
        this.getCommand("domination").setExecutor(new DominationCommand());

        getLogger().info("On");
        this.game.setGameStats(GameStats.WAITING);

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

        return (Domination) plugin;
    }

    public static Game getGameInstance() {
        final Domination dominationPlugin = getInstance();

        return dominationPlugin.getGame();
    }


}
