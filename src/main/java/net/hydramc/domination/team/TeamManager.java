package net.hydramc.domination.team;

import net.hydramc.domination.game.Game;
import org.bukkit.entity.Player;

public class TeamManager {

    private final Game game;

    public TeamManager(Game game) {
        this.game = game;
    }

    public Team getTeam(Player player) {

        Team red = game.getRed();
        if (red.isMember(player))
            return red;

        Team blue = game.getBlue();
        if (blue.isMember(player))
            return blue;

        Team random = game.getRandom();
        if (random.isMember(player))
            return random;

        return null;

    }

    public Team getEnemyTeam(Team team) {
        Team red = game.getRed();
        Team blue = game.getBlue();
        if (game.getRed() == team)
            return blue;
        return red;
    }

    public void removeTeam(Player player) {
        game.getRed().removePlayer(player);
        game.getBlue().removePlayer(player);
        game.getRandom().removePlayer(player);
    }

    public void setRandom(Player player) {
        game.getRed().removePlayer(player);
        game.getBlue().removePlayer(player);
        game.getRandom().addPlayer(player);
    }

    public void waitingTeam(Player player, Team team, Boolean nameTag) {

        Team before = getTeam(player);

        if (before != null) {
            if (before == team)
                return;
            before.removePlayer(player);
        }
        team.addPlayer(player);
        if (nameTag) {
            TeamColor teamColor = team.getTeamColor();
            NameTag.setWaitingNameTag(player, teamColor.getColor());
        }

    }

    public Team setRandomTeam(Player player) {

        Team red = game.getRed();
        Team blue = game.getBlue();
        Team random = game.getRandom();

        Team team = getTeam(player);

        if (team == null || random == team) {
            random.removePlayer(player);
            if (red.getSize() < blue.getSize()) {
                red.addPlayer(player);
                return red;
            }
            if (red.getSize() > blue.getSize()) {
                blue.addPlayer(player);
                return blue;
            }

            double randomNumber = Math.random();

            if (randomNumber > 0.5) {
                red.addPlayer(player);
                return red;
            }
            blue.addPlayer(player);
            return blue;
        }
        return team;

    }

}
