package net.hydramc.domination.team;

import io.netty.util.internal.StringUtil;
import me.neznamy.tab.api.TabAPI;
import me.neznamy.tab.api.TabPlayer;
import net.hydramc.domination.Domination;
import net.hydramc.domination.game.Game;
import org.bukkit.entity.Player;

public class TeamManager {

    private static final Game game = Domination.getGameInstance();

    public static Team getTeam(Player player) {

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
        game.getRed().removePlayer(player);
        game.getBlue().removePlayer(player);
        game.getRandom().removePlayer(player);
    }

    public static void setRandom(Player player) {
        game.getRed().removePlayer(player);
        game.getBlue().removePlayer(player);
        game.getRandom().addPlayer(player);
    }

    public static void waitingTeam(Player player, Team team, Boolean nameTag) {

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

    public static Team setRandomTeam(Player player) {

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
