package p.squidgames.manager;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import p.squidgames.Listener.PlayerJoinListener;
import p.squidgames.SquidGames;

public class PlaholderAPI extends PlaceholderExpansion {

    private SquidGames plugin;
    private PlayerJoinListener numero;
    public PlaholderAPI(SquidGames plugin, PlayerJoinListener numero) {
        this.numero = numero;
        this.plugin = plugin;
    }

    @Override
    public String getIdentifier() {
        return "SquidGames";
    }

    @Override
    public String getAuthor() {
        return "Spinnin34";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        if (player == null) {
            return "";
        }
        // Verificar si el placeholder solicitado es el que necesitamos
        if (identifier.equals("spinnin34_numero")) {
            String playerName = player.getName();
            // Obtener el número asignado al jugador desde el mapa playerSuffixMap
            int suffixNumber = numero.getPlayerSuffixMap().getOrDefault(playerName, 1);
            return String.valueOf(suffixNumber);
        }
        return null;
    }
}
