package p.squidgames.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import p.squidgames.SquidGames;
import p.squidgames.message.MessageUtils;
import p.squidgames.webhook.DiscordWebhookMessage;
import p.squidgames.webhook.WebhookUtil;

import java.util.List;

public class TareaCommand implements CommandExecutor {

    private SquidGames plugin;

    public TareaCommand(SquidGames plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Este comando solo puede ser ejecutado por un jugador.");
            return true;
        }

        if (!(sender.hasPermission("squidgames.dev.use.spinnin"))) {
            sender.sendMessage("No tienes permisos");
            return true;
        }

        Player player = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("tarea")) {
            if (args.length < 2) {

                player.sendMessage(MessageUtils.sendCenteredMessage(" "));
                player.sendMessage(MessageUtils.sendCenteredMessage("§x§1§3§E§1§9§DS§x§1§5§D§C§9§Bq§x§1§7§D§7§9§9u§x§1§A§D§3§9§6i§x§1§C§C§E§9§4d §x§1§E§C§9§9§2C§x§2§0§C§4§9§0o§x§2§2§B§F§8§Em§x§2§4§B§A§8§Cu§x§2§7§B§6§8§9n§x§2§9§B§1§8§7i§x§2§B§A§C§8§5t§x§2§D§A§7§8§3y §x§2§F§A§2§8§1G§x§3§1§9§D§7§Fa§x§3§4§9§9§7§Cm§x§3§6§9§4§7§Ae§x§3§8§8§F§7§8s"));
                player.sendMessage(MessageUtils.sendCenteredMessage(" "));
                player.sendMessage("§cᴄᴏᴍᴀɴᴅᴏꜱ ᴅɪꜱᴘᴏɴɪʙʟᴇꜱ:");
                player.sendMessage(" ");
                player.sendMessage("§f╔ §x§F§F§C§5§A§0 /tarea echo <texto>");
                player.sendMessage("§f╠ §x§F§F§C§5§A§0 /tarea pendiente <texto>");
                player.sendMessage("§f╚ §x§F§F§C§5§A§0 /tarea bug <texto>");
                player.sendMessage(" ");
                player.sendMessage("§7By the Developer ©Spinnin34");
                player.sendMessage(" ");
                return true;
            }

            String tipo = args[0].toLowerCase();
            StringBuilder texto = new StringBuilder();
            for (int i = 1; i < args.length; i++) {
                texto.append(args[i]).append(" ");
            }


            if (tipo.equals("pendiente")) {
                player.sendMessage("§x§8§0§E§1§8§C" + "Tarea pendiente: " + texto.toString());
                DiscordWebhookMessage message = createWebhookMessage(player.getName(), "Tarea Pendiente", texto.toString());
                WebhookUtil.sendDiscordWebhook("https://discord.com/api/webhooks/1223227725679038526/IkfWhvaxU6aHC2bIGK8oBtBdHG5JZXQnBfC8uoh8628yXWE7z1wLaLpwdGhDN6TGKYsQ", message);
            } else if (tipo.equals("echo")) {
                plugin.getServer().broadcastMessage("§x§8§0§E§1§8§C" + player.getName() + " ha completado la tarea: " + texto.toString());
                DiscordWebhookMessage message = createWebhookMessage(player.getName(), "Tarea Completada", texto.toString());
                WebhookUtil.sendDiscordWebhook("https://discord.com/api/webhooks/1223227725679038526/IkfWhvaxU6aHC2bIGK8oBtBdHG5JZXQnBfC8uoh8628yXWE7z1wLaLpwdGhDN6TGKYsQ", message);
            } else if (tipo.equals("bug")) {
                plugin.getServer().broadcastMessage("§x§8§0§E§1§8§C" + player.getName() + " ha encontrado un bug en la tarea: " + texto.toString());
                DiscordWebhookMessage message = createWebhookMessage(player.getName(), "Bug encontrado", texto.toString());
                WebhookUtil.sendDiscordWebhook("https://discord.com/api/webhooks/1223227725679038526/IkfWhvaxU6aHC2bIGK8oBtBdHG5JZXQnBfC8uoh8628yXWE7z1wLaLpwdGhDN6TGKYsQ", message);

            } else {
                player.sendMessage(MessageUtils.sendCenteredMessage(" "));
                player.sendMessage(MessageUtils.sendCenteredMessage("§x§1§3§E§1§9§DS§x§1§5§D§C§9§Bq§x§1§7§D§7§9§9u§x§1§A§D§3§9§6i§x§1§C§C§E§9§4d §x§1§E§C§9§9§2C§x§2§0§C§4§9§0o§x§2§2§B§F§8§Em§x§2§4§B§A§8§Cu§x§2§7§B§6§8§9n§x§2§9§B§1§8§7i§x§2§B§A§C§8§5t§x§2§D§A§7§8§3y §x§2§F§A§2§8§1G§x§3§1§9§D§7§Fa§x§3§4§9§9§7§Cm§x§3§6§9§4§7§Ae§x§3§8§8§F§7§8s"));
                player.sendMessage(MessageUtils.sendCenteredMessage(" "));
                player.sendMessage(MessageUtils.sendCenteredMessage("§c⚠ Tipo de tarea no válido. Debe ser 'pendiente' o 'echo' o 'bug'."));
                player.sendMessage(MessageUtils.sendCenteredMessage(" "));
                player.sendMessage(MessageUtils.sendCenteredMessage("§7By the Developer ©Spinnin34"));
                player.sendMessage(MessageUtils.sendCenteredMessage(" "));
            }

            return true;
        }

        return false;
    }

    private DiscordWebhookMessage createWebhookMessage(String playerName, String tareaType, String tareaText) {
        return new DiscordWebhookMessage(
                null,
                List.of(new DiscordWebhookMessage.Embed(
                        "Nueva Tarea: " + tareaType,
                        5814783,
                        List.of(
                                new DiscordWebhookMessage.Embed.Field("Jugador", playerName, false),
                                new DiscordWebhookMessage.Embed.Field("Tipo", tareaType, false),
                                new DiscordWebhookMessage.Embed.Field("Descripción", tareaText, false)
                        ),
                        new DiscordWebhookMessage.Embed.Author(playerName, "https://karmancos.42web.io/php/spinnin.png"),
                        new DiscordWebhookMessage.Embed.Footer("Developer By the Spinnin", "https://karmancos.42web.io/php/spinnin.png"),
                        new DiscordWebhookMessage.Embed.Thumbnail("https://visage.surgeplay.com/bust/" + playerName + ".png")
                ))
        );
    }
}


