package net.hydramc.domination.utils;

import net.hydramc.domination.Domination;
import org.bukkit.Location;

public class Locations {

    public static Location getSpawn(String team) {
        return (Location) Domination.getInstance().getConfig().get("spawn-team-" + team);
    }

}
