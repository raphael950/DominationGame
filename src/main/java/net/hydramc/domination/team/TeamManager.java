package net.hydramc.domination.team;

import org.bukkit.entity.Player;

import java.util.WeakHashMap;

public class TeamManager {

    private static WeakHashMap<Player, String> teams = new WeakHashMap<>();

    public static void setupTeams() {

        teams = new WeakHashMap<>();

    }

    public static String getTeam(Player player) {

        return teams.get(player);

    }

    public static Boolean hasTeam(Player player) {

        return teams.get(player) != null;

    }

    public static void setTeam(Player player, String team) {

        teams.put(player, team);

    }

    public static void setRandomTeam(Player player) {

        int redSize = 0;
        int blueSize = 0;

        for (Player loopPlayer : teams.keySet()) {
            if (hasTeam(loopPlayer)) {
                if (getTeam(loopPlayer).equals("red")) {
                    redSize++;
                } else {
                    blueSize++;
                }
            }
        }

        if (redSize >= blueSize) {
            TeamManager.setTeam(player, "red");
        } else {
            TeamManager.setTeam(player, "blue");
        }

    }

    public static void removeTeam(Player player) {

        teams.remove(player);

    }

}
