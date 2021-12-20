package net.hydramc.domination.event;

import net.hydramc.GameStats;
import net.hydramc.domination.Domination;
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
        return Domination.getGameInstance().getGameStats();
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
