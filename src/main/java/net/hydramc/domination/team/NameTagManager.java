package net.hydramc.domination.team;

import me.neznamy.tab.api.TabAPI;
import me.neznamy.tab.api.TabPlayer;
import me.neznamy.tab.api.team.TeamManager;
import org.bukkit.entity.Player;

public class NameTagManager {

    private final TabAPI tabInstance = TabAPI.getInstance();
    private final TeamManager tabTeamManager = tabInstance.getTeamManager();

    public NameTagManager() {
    }

    public void setNameTag(Player player, String prefix) {
        TabPlayer tabPlayer = tabInstance.getPlayer(player.getUniqueId());
        tabTeamManager.setPrefix(tabPlayer, prefix);
    }


    public void setWaitingNameTag(Player player, String color) {
        TabPlayer tabPlayer = tabInstance.getPlayer(player.getUniqueId());
        // Take the player's rank nametag
        String configNametag = tabTeamManager.getOriginalPrefix(tabPlayer);
        // Remove colors of the nametag because of the prefix limit of 16 caracters
        if (configNametag.length() > 10) {
            configNametag = configNametag.substring(2);
            configNametag = configNametag.substring(0, configNametag.length() - 2);
        }
        tabTeamManager.setPrefix(tabPlayer, color + configNametag + color);

    }

    public void defaultNameTagFromConfig(Player player) {
        TabPlayer tabPlayer = tabInstance.getPlayer(player.getUniqueId());
        tabTeamManager.resetPrefix(tabPlayer);
    }

}
