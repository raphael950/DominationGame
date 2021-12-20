package net.hydramc.domination.commands;

import net.hydramc.GameStats;
import net.hydramc.domination.Domination;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Objects;

public class Dm implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player)(sender);
        if (!player.hasPermission("group.admin")) {
            return false;
        }

        int argLenght = args.length;

        if (argLenght == 0) {
            return false;
        }

        if (argLenght == 1) {

            Plugin plugin = Domination.getInstance();
            assert plugin != null;
            Location location = player.getLocation();

            switch (args[0]) {

                case "setlobby":

                    plugin.getConfig().set("lobby-location", location);
                    plugin.saveConfig();
                    player.sendMessage("§8» §7La location du §elobby d'attente §7a été changé.");
                    return true;

                case "set":

                    player.sendMessage("§8/dm set §7[location name]");
                    return true;

                case "status":

                    String gameStats = String.valueOf(Objects.requireNonNull(Domination.getGameInstance()).getGameStats());
                    player.sendMessage("§8» §7Etat de la partie: §6" + gameStats);
                    return true;

                case "start":

                    if (Domination.getGameInstance().getGameStats() == GameStats.DURING) {
                        player.sendMessage("§8» §cLa partie est déjà en cours");
                        return true;
                    }

                    Domination.getGameInstance().setGameStats(GameStats.DURING);
                    player.sendMessage("§8» §7La partie a été lancée");
                    return true;

                case "stop":

                    if (!(Domination.getGameInstance().getGameStats() == GameStats.DURING)) {
                        player.sendMessage("§8» §cLa partie n'est pas en cours");
                        return true;
                    }

                    // TODO
                    // player.sendMessage("§8» §7La partie a été définie sur §6WAITING");
                    player.sendMessage("§8» §7Pas encore mis en place");
                    return true;

            }

        }

        return false;

    }

}