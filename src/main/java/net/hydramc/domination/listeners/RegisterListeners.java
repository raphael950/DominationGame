package net.hydramc.domination.listeners;

import fr.mrcubee.finder.plugin.PluginFinder;
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
                // Put all listeners's instance.
        };
        for (Listener listener : listeners)
            pluginManager.registerEvents(listener, plugin);
    }
}
