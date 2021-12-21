package net.hydramc.domination.commands;

import fr.mrcubee.langlib.Lang;
import net.hydramc.GameStats;
import net.hydramc.domination.Domination;
import net.hydramc.domination.Game;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.lang.reflect.Method;
import java.util.Arrays;

public class Dm implements CommandExecutor {

    private boolean setlobby(Domination plugin, Game game, Player sender, String[] args) {
        plugin.getConfig().set("lobby-location", sender.getLocation());
        sender.sendMessage(Lang.getMessage(sender, "command.dm.setlobby", "§8» §7La location du §elobby d'attente §7a été changé.", true));
        return true;
    }

    private boolean set(Domination plugin, Game game, Player sender, String[] args) {
        sender.sendMessage(Lang.getMessage(sender, "command.dm.set", "§8/dm set §7[location name]", true));
        // TODO: Set location of argument in config
        sender.sendMessage(Arrays.toString(args));
        return true;
    }

    private boolean status(Domination plugin, Game game, Player sender, String[] args) {
        sender.sendMessage(Lang.getMessage(sender,"", "§8» §7Etat de la partie: §6%s", true, game.getGameStats()));
        return true;
    }

    private boolean start(Domination plugin, Game game, Player sender, String[] args) {
        if (!game.getGameStats().equals(GameStats.WAITING))
            sender.sendMessage(Lang.getMessage("none", "§8» §cLa partie est déjà en cours", true));
        else if (game.setGameStats(GameStats.STARTING))
            sender.sendMessage(Lang.getMessage(sender, "none", "§8» §7La partie a été lancée", true));
        else
            sender.sendMessage(Lang.getMessage(sender, "none", "§8» §cLa partie ne peut pas encore être lancée", true));
        return true;
    }

    private boolean stop(Domination plugin, Game game, Player sender, String[] args) {
        if (!game.getGameStats().equals(GameStats.DURING))
            sender.sendMessage(Lang.getMessage(sender, "none", "§8» §cLa partie n'est pas en cours", true));
        else if (game.setGameStats(GameStats.STOPPING))
            sender.sendMessage(Lang.getMessage(sender, "none", "§8» §aLa partie a été arrêté", true));
        else
            sender.sendMessage(Lang.getMessage(sender, "none", "§8» §cLa partie ne peut pas encore être arrêtée", true));
        return true;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        final Domination plugin = Domination.getInstance();
        final Game game;
        final Player player;
        final String[] subCommandArgs;
        Method subCommandMethod = null;
        Object subCommandResult = null;

        if (plugin == null || !(sender instanceof Player) || !sender.hasPermission("group.admin") || args.length < 1)
            return false;
        game = plugin.getGame();
        player = (Player) sender;
        subCommandArgs = new String[args.length - 1];
        System.arraycopy(args, 1, subCommandArgs, 0, subCommandArgs.length);
        try {
            subCommandMethod = Dm.class.getDeclaredMethod(args[0].toLowerCase(), Domination.class, Game.class, Player.class, String[].class);
        } catch (NoSuchMethodException ignored) {}
        if (subCommandMethod == null)
            return false;
        subCommandMethod.setAccessible(true);
        try {
            subCommandResult = subCommandMethod.invoke(this, Domination.getInstance(), game, player, subCommandArgs);
        } catch (Exception exception) {
            player.sendMessage("JVM SubCommand Method Error: " + exception.getMessage());
        }
        if (subCommandResult instanceof Boolean)
            return (boolean) subCommandResult;
        return false;
    }
}