package net.hydramc.domination.player;

import fr.mrcubee.langlib.Lang;
import net.hydramc.domination.Domination;
import net.hydramc.domination.game.Game;
import net.hydramc.domination.team.Team;
import net.hydramc.domination.team.TeamManager;
import net.hydramc.domination.utils.Locations;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class DeathManager {

    private final Game game;
    private final TeamManager teamManager;
    private final PlayerStatsManager playerStatsManager;
    private BukkitTask task;
    private int count = 5;

    public DeathManager(Game game) {
        this.game = game;
        this.teamManager = game.getTeamManager();
        this.playerStatsManager = game.getPlayerStatsManager();
    }

    public void death(Player player) {
        Team team = game.getTeamManager().getTeam(player);
        PlayerData playerData = playerStatsManager.getOrCreatePlayerStats(player);

        playerData.setDead(true);
        player.setGameMode(GameMode.SPECTATOR);

        Bukkit.getScheduler().runTaskTimer(Domination.getInstance(), () -> {
            Bukkit.broadcastMessage(String.valueOf(count));
            if (count < 0) {
                playerData.setDead(false);
                player.setHealth(20);
                player.teleport(Locations.getSpawn(team.getName()));
                player.sendMessage(team.getName());
                player.playSound(player.getLocation(), Sound.LEVEL_UP, 1, 1);
                player.setGameMode(GameMode.SURVIVAL);
                task.cancel();
            }
            if (count == 0) {
                game.getTitleManager().sendSubtitle(player,8, 15, 5, Lang.getMessage(player, "game.during.respawning_action_bar", "ERROR", true));
            } else {
                game.getTitleManager().sendSubtitle(player,8, 15, 5, Lang.getMessage(player, "game.during.dead_action_bar", "ERROR", true, count));
            }
            count--;

        }, 0L, 20L);
    }

    public void death(Player player, Entity damagerEntity) {

        if (damagerEntity instanceof Player) {
            Team team = teamManager.getTeam(player);

            Player attacker = (Player) damagerEntity;

            String victimPrefix = team.getTeamColor().getColor() + player.getName();
            String damagerPrefix = teamManager.getTeam(attacker).getTeamColor().getColor() + attacker.getName();

            for (Player all : Bukkit.getOnlinePlayers()) {
                all.sendMessage(Lang.getMessage(all, "game.during.death_broadcast", "ERROR", true, victimPrefix, damagerPrefix));
            }
        }

        death(player);
    }

}
