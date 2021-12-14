package net.hydramc.domination.utils;

import fr.mrcubee.langlib.Lang;
import net.minecraft.server.v1_8_R3.ChatComponentText;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class ActionBar {

    public static void sendPlayerActionBar(Player player, String message) {
        PacketPlayOutChat packet = new PacketPlayOutChat(new ChatComponentText(message), (byte)2);
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
    }

    public static void sendGlobalActionBar(String message, Object... parameters) {
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            sendPlayerActionBar(player, Lang.getMessage(player, message, "ERROR", true, parameters));
        }
    }

}
