package p.squidgames.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class PlayerJoinListener implements Listener {

    private final JavaPlugin plugin;
    private final Map<String, Integer> playerSuffixMap;

    public PlayerJoinListener(JavaPlugin plugin) {
        this.plugin = plugin;
        this.playerSuffixMap = new HashMap<>();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        String playerName = event.getPlayer().getName();
        int suffixNumber = playerSuffixMap.getOrDefault(playerName, 0); // Obtener el número de sufijo del jugador
        if (suffixNumber == 0) { // Si el jugador no tiene un sufijo asignado
            suffixNumber = getNextAvailableSuffix(); // Obtener el siguiente número de sufijo disponible
            playerSuffixMap.put(playerName, suffixNumber); // Asignar el sufijo al jugador
        }
        event.getPlayer().setDisplayName(playerName + "§x§0§5§B§6§8§6#" +  suffixNumber + "§r");
        event.getPlayer().setLevel(suffixNumber); // Establecer el nivel del jugador al número de sufijo
    }

    private int getNextAvailableSuffix() {
        int suffix = 1;
        while (playerSuffixMap.containsValue(suffix)) {
            suffix++; // Incrementar el sufijo hasta que encuentres uno no utilizado
        }
        return suffix;
    }

    public Map<String, Integer> getPlayerSuffixMap() {
        return playerSuffixMap;
    }
}