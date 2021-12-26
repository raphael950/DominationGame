package net.hydramc.domination.player;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerStatsManager {

    private final Map<UUID, PlayerData> currentPlayerStats;

    public PlayerStatsManager() {
        this.currentPlayerStats = new HashMap<UUID, PlayerData>();
    }

    public PlayerData getPlayerStats(UUID uuid) {
        if (uuid == null)
            return null;
        return this.currentPlayerStats.get(uuid);
    }

    public PlayerData getPlayerStats(Player player) {
        if (player == null)
            return null;
        return this.currentPlayerStats.get(player.getUniqueId());
    }

    public PlayerData getOrCreatePlayerStats(UUID uuid) {
        PlayerData playerStats;

        if (uuid == null)
            return null;
        playerStats = this.currentPlayerStats.get(uuid);
        if (playerStats == null) {
            playerStats = new PlayerData();
            this.currentPlayerStats.put(uuid, playerStats);
        }
        return playerStats;
    }

    public PlayerData getOrCreatePlayerStats(Player player) {
        PlayerData playerStats;

        if (player == null)
            return null;
        playerStats = this.currentPlayerStats.get(player.getUniqueId());
        if (playerStats == null) {
            playerStats = new PlayerData();
            this.currentPlayerStats.put(player.getUniqueId(), playerStats);
        }
        return playerStats;
    }
    
}
