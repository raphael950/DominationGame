package net.hydramc.domination.team;

public class TeamColor {

    private final String prefix;
    private final String color;

    public TeamColor(final String prefix, final String color) {
        this.prefix = prefix;
        this.color = color;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getColor() {
        return color;
    }

}
