package p.squidgames.pruebas.luzverde;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import p.squidgames.SquidGames;

public class LuzManagerCommand implements CommandExecutor {
    private final SquidGames plugin;
    private final LuzVerdeLuzRojaListener listener;

    public LuzManagerCommand(SquidGames plugin, LuzVerdeLuzRojaListener listener) {
        this.plugin = plugin;
        this.listener = listener;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1 && args[0].equalsIgnoreCase("on")) {
            listener.enable();
            sender.sendMessage("Listener activado.");
            return true;
        } else if (args.length == 1 && args[0].equalsIgnoreCase("off")) {
            listener.disable();
            sender.sendMessage("Listener desactivado.");
            return true;
        } else {
            sender.sendMessage("Uso: /luzmanager <on/off>");
            return false;
        }
    }
}
