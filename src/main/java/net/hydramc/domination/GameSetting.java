package net.hydramc.domination;

import fr.mrcubee.annotation.spigot.config.Config;

public class GameSetting {

    @Config(path = "world.name")
    private String worldName = "world";

    @Config(path = "player.min")
    private int minPlayer = 10;

    @Config(path = "player.max")
    private int maxPlayer = 100;


    public String getWorldName() {
        return this.worldName;
    }

    public int getMinPlayer() {
        return this.minPlayer;
    }

    public int getMaxPlayer() {
        return this.maxPlayer;
    }
}
