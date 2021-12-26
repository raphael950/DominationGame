package net.hydramc.domination.team;

import org.bukkit.Bukkit;

public class TeamColor {

    private final String prefix;
    private final String color;
    private final String icon;

    public TeamColor(final String prefix, final String color, final String icon) {
        this.prefix = prefix;
        this.color = color;
        this.icon = icon;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getColor() {
        return color;
    }

    public String getIcon() {
        return icon;
    }
}
