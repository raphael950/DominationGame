package net.hydramc.domination.listeners.player;

import net.hydramc.domination.Domination;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.ChatColor;
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

        if (player.hasPermission("group.staff") || player.hasPermission("group.vip"))
            message = ChatColor.translateAlternateColorCodes('&', message);

        // TODO

        BaseComponent[] component = new ComponentBuilder("§c§l⚠")
                .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/report " + player.getName() + message))
                .event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§cCliquez pour Signaler").create()))
                .reset()
                .append(player.getName() + " §8» §7" + message)
                .create();

        Domination.getInstance().getServer().spigot().broadcast(component);

    }

}
