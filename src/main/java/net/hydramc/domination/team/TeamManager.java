package net.hydramc.domination.team;

import net.hydramc.domination.game.Game;
import net.hydramc.domination.player.PlayerData;
import net.hydramc.domination.player.PlayerStatsManager;
import net.hydramc.domination.utils.Config;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class TeamManager {

    private final Game game;
    private final PlayerStatsManager playerStatsManager;
    private final HashMap<String, Team> teams = new HashMap<>();

    public TeamManager(Game game) {
        this.game = game;
        this.playerStatsManager = game.getPlayerStatsManager();
        Config locationConfig = game.getLocationConfig();
        registerTeam(new Team("red", new TeamColor("Rouge", "&c"), new Region((Location) locationConfig.getConfig().get("center-red"), 30)));
        registerTeam(new Team("blue", new TeamColor("Bleue", "&9"), new Region((Location) locationConfig.getConfig().get("center-blue"), 30)));
    }

    private void registerTeam(Team team) {
        teams.put(team.getName(), team);
    }

    public Team getTeam(String name) {
        return teams.get(name);
    }

    /**
     * You can also get the team directly in PlayerData.
     */
    public Team getTeam(Player player) {
        return playerStatsManager.getOrCreatePlayerStats(player).getTeam();
    }

    public void removeTeam(Player player) {
        setTeam(player, (Team) null);
    }

    public void setTeam(Player player, Team team) {
        Team previousTeam = getTeam(player);
        PlayerData playerData = playerStatsManager.getOrCreatePlayerStats(player);

        if (previousTeam != null) {
            if (previousTeam == team) return;
            previousTeam.removePlayer(player);
            playerData.setTeam(null);
        }

        playerData.setTeam(team);
        if (team != null)
            team.addPlayer(player);
    }

    public void setTeam(Player player, String team) {
        setTeam(player, getTeam(team));
    }

    public Team getEnemyTeam(Team team) {
        HashMap<String, Team> clone = (HashMap<String, Team>) teams.clone();
        clone.remove(team.getName());
        return clone.values().stream().findFirst().get();
    }

    public Team getOrGiveTeam(Player player) {
        Team red = getTeam("red");
        Team blue = getTeam("blue");

        PlayerData playerData = playerStatsManager.getOrCreatePlayerStats(player);
        Team team = playerData.getTeam();

        if (team == null) {
            if (red.getSize() < blue.getSize()) {
                setTeam(player, red);
                return red;
            }
            if (red.getSize() > blue.getSize()) {
                setTeam(player, blue);
                return blue;
            }

            double randomNumber = Math.random();

            if (randomNumber > 0.5) {
                setTeam(player, blue);
                return red;
            }
            setTeam(player, blue);
            return blue;
        }
        return team;
    }

}
