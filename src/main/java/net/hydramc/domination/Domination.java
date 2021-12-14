package net.hydramc.domination;

import net.hydramc.domination.handlers.GameState;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Domination extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("[Domination] On");
        GameState.setState(GameState.WAITTING);
    }

    @Override
    public void onDisable() {
        GameState.setState(GameState.OFF);
        Bukkit.getLogger().info("[Domination] Off");
    }

}
