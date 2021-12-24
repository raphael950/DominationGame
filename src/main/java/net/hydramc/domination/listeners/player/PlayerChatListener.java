package net.hydramc.domination.listeners.player;

import net.hydramc.domination.Domination;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {

        event.setCancelled(true);

        Player player = event.getPlayer();
        String message = event.getMessage();

        // TODO

        BaseComponent[] component = new ComponentBuilder("§oreport logo")
                .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/report " + player.getName() + message))
                .append(player.getName() + " >> " + message)
                .create();

        Domination.getInstance().getServer().spigot().broadcast(component);

    }

}
