package net.hydramc.domination;

import net.hydramc.GameStats;
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
}
