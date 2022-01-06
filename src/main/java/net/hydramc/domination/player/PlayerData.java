package net.hydramc.domination.player;

public class PlayerData {

    public int kills;
    public int deaths;
    public boolean isDead;

    public PlayerData() {
        this.kills = 0;
        this.deaths = 0;
        this.isDead = false;
    }

    public int getKills() {
        return kills;
    }

    public void addKill() {
        kills++;
    }

    public int getDeaths() {
        return deaths;
    }

    public void addDeath() {
        deaths++;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }
}
