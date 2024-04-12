package p.squidgames.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import p.squidgames.message.MessageUtils;

public class NumeroCommad implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("lolame.admin.numero")) {
            sender.sendMessage("§f[§cLolame Main§f] §cNo tienes permisos.");
            return true;
        }
        if (command.getName().equalsIgnoreCase("numero")) {
            if (args.length < 1 || args.length > 2) {
                sender.sendMessage("Uso: /numero <numero> [jugador]");
                return true;
            }
            int numero;
            try {
                numero = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                sender.sendMessage("§f[§x§8§0§E§1§8§CS§x§7§8§D§8§8§Aq§x§7§0§C§F§8§8u§x§6§8§C§6§8§5i§x§6§0§B§D§8§3d§x§5§8§B§3§8§1G§x§5§0§A§A§7§Fa§x§4§8§A§1§7§Cm§x§4§0§9§8§7§Ae§x§3§8§8§F§7§8s§f] §cEse numero no es valido");
                return true;
            }

            if (args.length == 1) {
                if (!(sender instanceof Player)){
                    sender.sendMessage("§f[§x§8§0§E§1§8§CS§x§7§8§D§8§8§Aq§x§7§0§C§F§8§8u§x§6§8§C§6§8§5i§x§6§0§B§D§8§3d§x§5§8§B§3§8§1G§x§5§0§A§A§7§Fa§x§4§8§A§1§7§Cm§x§4§0§9§8§7§Ae§x§3§8§8§F§7§8s§f] §cDebes ser un jugador para usar este comando sin especificar un jugador");
                    return true;
                }
                Player player = (Player) sender;
                player.setDisplayName(player.getName() + "§x§0§5§B§6§8§6#" + numero);
                sender.sendMessage(MessageUtils.sendCenteredMessage(" "));
                sender.sendMessage(MessageUtils.sendCenteredMessage("§x§8§0§E§1§8§CS§x§7§8§D§8§8§Aq§x§7§0§C§F§8§8u§x§6§8§C§6§8§5i§x§6§0§B§D§8§3d§x§5§8§B§3§8§1G§x§5§0§A§A§7§Fa§x§4§8§A§1§7§Cm§x§4§0§9§8§7§Ae§x§3§8§8§F§7§8s"));
                sender.sendMessage(MessageUtils.sendCenteredMessage(" "));
                sender.sendMessage(MessageUtils.sendCenteredMessage("§fTu numero ha sido establecido como §x§0§5§B§6§8§6#" + numero));
                sender.sendMessage(MessageUtils.sendCenteredMessage(" "));
                sender.sendMessage(MessageUtils.sendCenteredMessage("§7Developer ©Spinnin34"));
                sender.sendMessage(MessageUtils.sendCenteredMessage(" "));
            } else {
                Player target = Bukkit.getPlayer(args[1]);
                if (target == null || !target.isOnline()) {
                    sender.sendMessage("§f[§x§8§0§E§1§8§CS§x§7§8§D§8§8§Aq§x§7§0§C§F§8§8u§x§6§8§C§6§8§5i§x§6§0§B§D§8§3d§x§5§8§B§3§8§1G§x§5§0§A§A§7§Fa§x§4§8§A§1§7§Cm§x§4§0§9§8§7§Ae§x§3§8§8§F§7§8s§f] §cNo se encotro al jugador");
                    return true;
                }
                target.getPlayer().setLevel(numero);
                target.setDisplayName(target.getName() + "§x§0§5§B§6§8§6#" + numero);
                sender.sendMessage(MessageUtils.sendCenteredMessage(" "));
                sender.sendMessage(MessageUtils.sendCenteredMessage("§x§8§0§E§1§8§CS§x§7§8§D§8§8§Aq§x§7§0§C§F§8§8u§x§6§8§C§6§8§5i§x§6§0§B§D§8§3d§x§5§8§B§3§8§1G§x§5§0§A§A§7§Fa§x§4§8§A§1§7§Cm§x§4§0§9§8§7§Ae§x§3§8§8§F§7§8s"));
                sender.sendMessage(MessageUtils.sendCenteredMessage(" "));
                sender.sendMessage(MessageUtils.sendCenteredMessage("§7El numero de §c" + target.getName() + "§7 ha sido establecido como §x§0§5§B§6§8§6#" + numero));
                sender.sendMessage(MessageUtils.sendCenteredMessage(" "));
                sender.sendMessage(MessageUtils.sendCenteredMessage("§7Developer ©Spinnin34"));
                sender.sendMessage(MessageUtils.sendCenteredMessage(" "));
            }

            return true;
        }
        return false;
    }
}