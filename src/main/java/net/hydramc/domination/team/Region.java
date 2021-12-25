package net.hydramc.domination.team;

import org.bukkit.Location;

public class Region {

    private Location center;
    private int radius;

    public Region(Location center, int radius) {
        this.center = center;
        this.radius = radius;
    }

    public Location getCenter() {
        return center;
    }

    public void setCenter(Location center) {
        this.center = center;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public boolean isInCircle(Location location) {
        return Math.max(location.getBlockX(), center.getBlockX()) - Math.min(location.getBlockX(), center.getBlockX()) <= radius && Math.max(location.getBlockZ(), center.getBlockZ()) - Math.min(location.getBlockZ(), center.getBlockZ()) <= radius;
    }

    public boolean isInSquare(Location location) {
        int z = Math.max(location.getBlockZ(), center.getBlockZ()) - Math.min(location.getBlockZ(), center.getBlockZ());
        int x = Math.max(location.getBlockX(), center.getBlockX()) - Math.min(location.getBlockX(), center.getBlockX());
        return Math.max(z, x) <= radius;
    }


}
