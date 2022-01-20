package net.hydramc.domination.listeners.game;

import net.hydramc.domination.Domination;
import net.hydramc.domination.event.EscortedEntityChangeTeamTarget;
import net.hydramc.domination.game.Game;
import net.hydramc.domination.player.PlayerManager;
import net.hydramc.domination.team.Team;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class EscortedEntityChangeTeamTargetListener implements Listener {

    Game game = Domination.getGameInstance();
    PlayerManager playerManager = game.getPlayerManager();

    @EventHandler
    public void onTargetChange(EscortedEntityChangeTeamTarget event) {
        Team newTarget = event.getTarget();
        playerManager.broadcast("game.target_change", newTarget);
    }

}
