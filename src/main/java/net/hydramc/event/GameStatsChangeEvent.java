package net.hydramc.event;

import net.hydramc.GameStats;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class GameStatsChangeEvent extends Event {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final GameStats newGameStats;

    public GameStatsChangeEvent(GameStats newGameStats) {
        this.newGameStats = newGameStats;
    }

    public GameStats getNewGameStats() {
        return this.newGameStats;
    }

    public GameStats getCurrentGameStats() {
        // TODO: Get game's current stats.
        return null;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
