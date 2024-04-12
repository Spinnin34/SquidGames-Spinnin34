package p.squidgames.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import p.squidgames.SquidGames;
import p.squidgames.webhook.DiscordWebhookMessage;
import p.squidgames.webhook.WebhookUtil;

import java.util.List;

public class DeathMessageListener implements Listener {
    private PlayerJoinListener numero;

    private final SquidGames plugin;

    public DeathMessageListener(SquidGames plugin, PlayerJoinListener numero) {
        this.numero = numero;
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();

        DiscordWebhookMessage message = createWebhookMessage(player.getName());
        WebhookUtil.sendDiscordWebhook("https://discord.com/api/webhooks/1223331545683595428/077IyY_-vfvG2ue8F7FOJ_yb2UP-jQHjcQoVuKeZRzMUWCi-RbU2gTsouQTF8M9TODcR", message);
        String deathMessage = event.getDeathMessage();
        String playerName = event.getEntity().getName();
        String customDeathMessage = "Jugador §x§0§5§B§6§8§6" + playerName + numero.getPlayerSuffixMap() + " a sido eliminado.";

        event.setDeathMessage(customDeathMessage);
    }


    private DiscordWebhookMessage createWebhookMessage(String playerName) {
        return new DiscordWebhookMessage(
                null,
                List.of(new DiscordWebhookMessage.Embed(
                        "Eliminación: SquidGames",
                        5814783,
                        List.of(
                                new DiscordWebhookMessage.Embed.Field("Jugador", playerName + numero.getPlayerSuffixMap() + " a sido eliminado.", false)
                        ),
                        new DiscordWebhookMessage.Embed.Author(playerName, "https://karmancos.42web.io/php/spinnin.png"),
                        new DiscordWebhookMessage.Embed.Footer("Developer By the Spinnin", "https://karmancos.42web.io/php/spinnin.png"),
                        new DiscordWebhookMessage.Embed.Thumbnail("https://visage.surgeplay.com/bust/" + playerName + ".png")
                ))
        );
    }
}

