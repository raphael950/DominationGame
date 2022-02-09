package net.hydramc.domination.player;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerStatsManager {

    private final Map<UUID, PlayerData> currentPlayerStats;

    public PlayerStatsManager() {
        this.currentPlayerStats = new HashMap<>();
    }

    public PlayerData getOrCreatePlayerStats(UUID uuid) {
        PlayerData playerStats;

        if (uuid == null)
            return null;
        playerStats = this.currentPlayerStats.get(uuid);
        if (playerStats == null) {
            playerStats = new PlayerData(uuid);
            this.currentPlayerStats.put(uuid, playerStats);
        }
        return playerStats;
    }

    public PlayerData getOrCreatePlayerStats(Player player) {
        return getOrCreatePlayerStats(player.getUniqueId());
    }
    
}
