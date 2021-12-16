package net.hydramc.domination.utils;

import org.bukkit.entity.Player;

import java.util.WeakHashMap;

public class Cooldown {

    public static WeakHashMap<Player, Long> cooldowns;

    public static void setupCooldown() {
        cooldowns = new WeakHashMap<>();
    }

    public static boolean hasCooldown(Player player) {
        Long cooldown = cooldowns.get(player);
        if (cooldown != null) {
            if (cooldown >= System.currentTimeMillis()) {
                return true;
            }
            cooldowns.remove(player);
        }
        return false;
    }

    public static long getCooldown(Player player) {
        Long cooldown = cooldowns.get(player);
        if (cooldown == null)
            return -1;
        return (cooldown - System.currentTimeMillis())/1000;
    }

    public static void setCooldown(Player player, int seconds) {
        Long delay = System.currentTimeMillis() + (seconds * 1000L);
        cooldowns.put(player, delay);
    }

}
