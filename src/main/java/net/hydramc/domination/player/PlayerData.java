package net.hydramc.domination.player;

import org.bukkit.entity.Player;

import java.util.WeakHashMap;

public class PlayerData {

    private final Player player;
    private Boolean isDead;
    private Boolean isInBase;
    private Boolean isInEnemyBase;
    private int kills;
    private int deaths;
    public static WeakHashMap<Player, PlayerData> playerDatas = new WeakHashMap<>();

    public PlayerData(Player player) {
        this.player = player;
        this.kills = 0;
        this.deaths = 0;
        this.isDead = false;
        playerDatas.put(player, this);
    }

    public Player getPlayer() {
        return player;
    }

    public Boolean getDead() {
        return isDead;
    }

    public void setDead(Boolean dead) {
        isDead = dead;
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

    public Boolean getInBase() {
        return isInBase;
    }

    public void setInBase(Boolean inBase) {
        isInBase = inBase;
    }

    public Boolean getInEnemyBase() {
        return isInEnemyBase;
    }

    public void setInEnemyBase(Boolean inEnemyBase) {
        isInEnemyBase = inEnemyBase;
    }

    public void delete() {
        playerDatas.remove(player);
    }

    public static PlayerData get(Player player) {
        return playerDatas.get(player);
    }

}
