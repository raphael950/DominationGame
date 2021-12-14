package net.hydramc.domination;

import net.hydramc.domination.handlers.GameState;
import org.bukkit.plugin.java.JavaPlugin;

public class Domination extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("[Domination] On");
        GameState.setState(GameState.WAITTING);
    }

    @Override
    public void onDisable() {
        GameState.setState(GameState.OFF);
        getLogger().info("[Domination] Off");
    }

}
