package net.hydramc.domination.team;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TeamBuilder {

    private final String team;
    private final String prefix;
    private final String prefitFormatted;
    private List<Player> players;

    public TeamBuilder(String team, String prefix, String prefitFormatted) {
        this.team = team;
        this.prefix = prefix;
        this.prefitFormatted = prefitFormatted;
        this.players = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        if (!players.contains(player))
            players.add(player);
    }

    public void removePlayer(Player player) {
        if (players.contains(player))
            players.remove(player);
    }

    public String getTeam() {
        return team;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getPrefitFormatted() {
        return prefitFormatted;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
