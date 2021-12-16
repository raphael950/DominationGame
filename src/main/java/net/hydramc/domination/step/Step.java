package net.hydramc.domination.step;

import net.hydramc.domination.Game;
import net.hydramc.domination.scoreboard.ScoreboardBuilder;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Objects;

public abstract class Step {

    private final String name;
    private final long duringTime;
    private long startTime;

    protected Step(String name, long duringTime) {
        this.name = name;
        this.duringTime = duringTime;
        this.startTime = 0;
    }

    protected Step(String name) {
        this(name, -1);
    }

    public String getName() {
        return this.name;
    }

    public long getDuringTime() {
        return this.duringTime;
    }

    public long getTimeMillis() {
        if (this.startTime == 0)
            return -1;
        return System.currentTimeMillis() - this.startTime;
    }

    public long getEndTimeMillis() {
        long time;

        if (this.startTime == 0 || this.duringTime < 0)
            return -1;
        time = System.currentTimeMillis() - this.startTime;
        if (time > this.duringTime)
            return 0;
        return this.duringTime - time;
    }

    protected void start() {
        this.startTime = System.currentTimeMillis();
    }

    protected void complete() {
        this.startTime = 0;
    }

    protected void remove() {
        this.startTime = 0;
    }

    public abstract void update();

    public List<String> updateScoreboard(Player player, Game game) {
        return ScoreboardBuilder.build(player, game);
    }

    public boolean canStepBack() {
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.name);
    }

    @Override
    public boolean equals(Object object) {
        return (object instanceof Step) && object.hashCode() == hashCode();
    }

}
