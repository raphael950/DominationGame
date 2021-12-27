package net.hydramc.domination.team;

import fr.mrcubee.weak.WeakHashSet;
import org.bukkit.entity.Player;

public class Team {

    private final String name;
    private final TeamColor teamColor;
    private final Region region;
    private WeakHashSet<Player> players;

    public Team(final String name, final TeamColor teamColor) {
        this.name = name;
        this.teamColor = teamColor;
        this.players = new WeakHashSet<>();
        this.region = null;
    }

    public Team(final String name, final TeamColor teamColor, Region region) {
        this.name = name;
        this.teamColor = teamColor;
        this.players = new WeakHashSet<>();
        this.region = region;
    }

    public void addPlayer(final Player player) {
        if (!this.players.contains(player)) {
            this.players.add(player);
        }
    }

    public void removePlayer(final Player player) {
        this.players.remove(player);
    }

    public boolean isMember(final Player player) {
        return this.players.contains(player);
    }

    public String getName() {
        return this.name;
    }

    public TeamColor getTeamColor() {
        return teamColor;
    }

    public Region getRegion() {
        return this.region;
    }

    public WeakHashSet<Player> getPlayers() {
        return this.players;
    }

    public int getSize() {
        return this.players.size();
    }

    public void setPlayers(final WeakHashSet<Player> players) {
        this.players = players;
    }

}
