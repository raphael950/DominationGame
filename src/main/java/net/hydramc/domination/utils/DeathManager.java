package net.hydramc.domination.utils;

import fr.mrcubee.langlib.Lang;
import net.hydramc.domination.Domination;
import net.hydramc.domination.game.Game;
import net.hydramc.domination.player.PlayerData;
import net.hydramc.domination.player.PlayerStatsManager;
import net.hydramc.domination.team.Team;
import net.hydramc.domination.team.TeamManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.concurrent.atomic.AtomicInteger;

public class DeathManager {

    private final Game game = Domination.getGameInstance();
    private final TeamManager teamManager = game.getTeamManager();
    private final PlayerStatsManager playerStatsManager = game.getPlayerStatsManager();
    private BukkitTask countDown;

    public void death(Player player) {
        Team team = game.getTeamManager().getTeam(player);
        PlayerData playerData = playerStatsManager.getOrCreatePlayerStats(player);

        playerData.setDead(true);
        player.setGameMode(GameMode.SPECTATOR);

        AtomicInteger seconds = new AtomicInteger(5);
        countDown = Domination.getInstance().getServer().getScheduler().runTaskTimer(Domination.getInstance(), () -> {
            if (seconds.intValue() < 0) {
                countDown.cancel();
                playerData.setDead(false);
                player.teleport(Locations.getSpawn(team.getName()));
                player.playSound(player.getLocation(), Sound.LEVEL_UP, 1, 1);
                player.setGameMode(GameMode.SURVIVAL);
                return;
            }
            if (seconds.intValue() == 0) {
                game.getTitleManager().sendSubtitle(player,8, 15, 5, Lang.getMessage(player, "game.during.respawning_action_bar", "ERROR", true));
            } else {
                game.getTitleManager().sendSubtitle(player,8, 15, 5, Lang.getMessage(player, "game.during.dead_action_bar", "ERROR", true, seconds));
            }
            seconds.getAndDecrement();

        }, 0L, 20L);
    }

    public void death(Player player, Entity damagerEntity) {
        Team team = teamManager.getTeam(player);
        PlayerData playerData = playerStatsManager.getOrCreatePlayerStats(player);

        playerData.setDead(true);
        player.setGameMode(GameMode.SPECTATOR);
        player.setSpectatorTarget(damagerEntity);

        if (damagerEntity instanceof Player) {
            Player attacker = (Player) damagerEntity;

            String victimPrefix = team.getTeamColor().getColor() + player.getName();
            String damagerPrefix = teamManager.getTeam(attacker).getTeamColor().getColor() + attacker.getName();

            for (Player all : Bukkit.getOnlinePlayers()) {
                all.sendMessage(Lang.getMessage(all, "game.during.death_broadcast", "ERROR", true, victimPrefix, damagerPrefix));
            }
        }

        AtomicInteger seconds = new AtomicInteger(5);
        countDown = Domination.getInstance().getServer().getScheduler().runTaskTimer(Domination.getInstance(), () -> {
            if (seconds.intValue() < 0) {
                countDown.cancel();
                playerData.setDead(false);
                player.teleport(Locations.getSpawn(team.getName()));
                player.playSound(player.getLocation(), Sound.LEVEL_UP, 1, 1);
                player.setGameMode(GameMode.SURVIVAL);
                return;
            }
            if (seconds.intValue() == 0) {
                game.getTitleManager().sendSubtitle(player,8, 15, 5, Lang.getMessage(player, "game.during.respawning_action_bar", "ERROR", true));
            } else {
                game.getTitleManager().sendSubtitle(player,8, 15, 5, Lang.getMessage(player, "game.during.dead_action_bar", "ERROR", true, seconds));
            }
            seconds.getAndDecrement();

        }, 0L, 20L);
    }

}
