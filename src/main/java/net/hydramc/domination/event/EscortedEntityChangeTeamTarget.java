package net.hydramc.domination.event;

import net.hydramc.domination.entity.EscortedEntity;
import net.hydramc.domination.team.Team;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class EscortedEntityChangeTeamTarget extends Event implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final EscortedEntity escortedEntity;
    private final Team lastTarget;
    private Team target;
    private boolean cancelled;

    public EscortedEntityChangeTeamTarget(EscortedEntity escortedEntity, Team lastTarget, Team target) {
        this.escortedEntity = escortedEntity;
        this.lastTarget = lastTarget;
        this.target = target;
        this.cancelled = false;
    }

    public Team getLastTarget() {
        return this.lastTarget;
    }

    public Team getTarget() {
        return this.target;
    }

    public void setTarget(Team target) {
        this.target = target;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
