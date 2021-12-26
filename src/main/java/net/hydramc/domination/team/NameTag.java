package net.hydramc.domination.team;

import me.neznamy.tab.api.TabAPI;
import me.neznamy.tab.api.TabPlayer;
import me.neznamy.tab.api.team.TeamManager;
import org.bukkit.entity.Player;

public class NameTag {

    private static final TabAPI instance = TabAPI.getInstance();
    private static final TeamManager teamManager = instance.getTeamManager();

    public static void setNameTag(Player player, String prefix){

        TabPlayer tabPlayer = instance.getPlayer(player.getUniqueId());
        teamManager.setPrefix(tabPlayer, prefix + " ");

    }


    public static void setWaitingNameTag(Player player, String color){

        TabPlayer tabPlayer = instance.getPlayer(player.getUniqueId());

        // Take the player's rank nametag
        String configNametag = teamManager.getOriginalPrefix(tabPlayer);

        // Remove colors of the nametag because of the prefix limit of 16 caracters
        if (configNametag.length() > 10) {
            configNametag = configNametag.substring(2);
            configNametag = configNametag.substring(0, configNametag.length() - 2);
        }
        teamManager.setPrefix(tabPlayer, color + configNametag + color);

    }

    public static void defaultNameTag(Player player){

        TabPlayer tabPlayer = instance.getPlayer(player.getUniqueId());
        teamManager.resetPrefix(tabPlayer);

    }

}
