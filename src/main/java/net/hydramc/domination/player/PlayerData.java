package net.hydramc.domination.player;

import org.bukkit.entity.Player;

public class PlayerData {

    private final Player player;
    private int kills;
    private int deaths;

    public PlayerData(Player player) {
        this.player = player;
        this.kills = 0;
        this.deaths = 0;
    }

    public Player getPlayer() {
        return player;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public void addKill(int kill) {
        this.kills += kill;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public void addDeath(int death) {
        this.deaths += death;
    }

}
