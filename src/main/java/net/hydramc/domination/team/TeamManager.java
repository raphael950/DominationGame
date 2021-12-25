package net.hydramc.domination.team;

import net.hydramc.domination.Domination;
import net.hydramc.domination.game.Game;
import org.bukkit.entity.Player;

public class TeamManager {

    public static Team getTeam(Player player) {

        final Game game = Domination.getGameInstance();

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

    public static void removeTeam(Player player) {
        final Game game = Domination.getGameInstance();
        game.getRed().removePlayer(player);
        game.getBlue().removePlayer(player);
        game.getRandom().removePlayer(player);
    }

    public static void forceTeam(Player player, Team team) {

        Team before = getTeam(player);

        if (before == team)
            return;
        if (before != null) {
            before.removePlayer(player);
        }
        team.addPlayer(player);

    }

    public static Team setRandomTeam(Player player) {

        final Game game = Domination.getGameInstance();
        Team red = game.getRed();
        Team blue = game.getBlue();

        Team team = getTeam(player);

        if (team == null || game.getRandom().equals(team)) {
            if (red.getSize() < blue.getSize()) {
                red.addPlayer(player);
                return red;
            }
            if (red.getSize() > blue.getSize()) {
                blue.addPlayer(player);
                return blue;
            }
            double random = Math.random();

            if (random > 0.5) {
                red.addPlayer(player);
                return red;
            }
            blue.addPlayer(player);
            return blue;
        }
        return team;

    }

}
