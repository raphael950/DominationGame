package net.hydramc.util;

import org.bukkit.Bukkit;

public class Version {

    public static String getNMSVersion() {
        final String packageName = Bukkit.getServer().getClass().getPackage().getName();

        return packageName.substring(packageName.lastIndexOf('.') + 1);
    }

    public static Class<?> getNMSClass(String className) {
        if (className == null)
            return null;
        try {
            return Class.forName(String.format(className, getNMSVersion()));
        } catch (ClassNotFoundException ignored) {}
        return null;
    }
    
}
