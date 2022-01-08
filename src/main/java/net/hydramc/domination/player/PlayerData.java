package net.hydramc.domination.player;

import net.hydramc.domination.team.Team;

import java.util.UUID;

public class PlayerData {

    private final UUID uuid;
    private int kills;
    private int deaths;
    private boolean isDead;
    private Team team;


    public PlayerData(UUID uuid) {
        this.uuid = uuid;
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

    public void setTeam(Team team) {
        this.team = team;
    }

    public Team getTeam() {
        return team;
    }
}
