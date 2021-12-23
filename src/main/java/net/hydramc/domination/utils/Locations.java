package net.hydramc.domination.utils;

import net.hydramc.domination.Domination;
import org.bukkit.Location;

public class Locations {

    public static Location getSpawn(String team) {
        return (Location) Domination.getGameInstance().getLocationConfig().getConfig().get("spawn-" + team);
    }

    public static Location getLobby() {
        return (Location) Domination.getGameInstance().getLocationConfig().getConfig().get("lobby");
    }

}
