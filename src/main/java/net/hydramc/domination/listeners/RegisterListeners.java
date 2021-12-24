package net.hydramc.domination.listeners;

import fr.mrcubee.finder.plugin.PluginFinder;
import net.hydramc.domination.listeners.entity.DamageListener;
import net.hydramc.domination.listeners.game.GameStatsChangeListener;
import net.hydramc.domination.listeners.player.*;
import net.hydramc.domination.listeners.world.BlockPhysicListener;
import net.hydramc.domination.listeners.world.WeatherListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class RegisterListeners {

    public static void register() {
        final Plugin plugin = (Plugin) PluginFinder.INSTANCE.findPluginCaller();
        final PluginManager pluginManager;
        final Listener[] listeners;

        if (plugin == null)
            return;
        pluginManager = Bukkit.getPluginManager();
        listeners = new Listener[] {
                // Put all listener's instance.

                new DamageListener(),

                new GameStatsChangeListener(),

                new PlayerBlockListener(),
                new PlayerChatListener(),
                new PlayerDropItemListener(),
                new PlayerFoodLevelListener(),
                new PlayerInteractListener(),
                new PlayerJoinListener(),
                new PlayerQuitListener(),

                new BlockPhysicListener(),
                new WeatherListener()
        };
        for (Listener listener : listeners)
            pluginManager.registerEvents(listener, plugin);
    }
}
