package net.hydramc.domination.commands;

import net.hydramc.GameStats;
import net.hydramc.domination.Domination;
import net.hydramc.domination.game.Game;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {

    private final Game game = Domination.getGameInstance();

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        final GameStats gameStats = game.getGameStats();

        if (gameStats != GameStats.WAITING)
            return true;

        if (sender instanceof Player) {
            game.getPlayerManager().teleportToSpawn((Player) sender);
        }
        return true;
    }

}