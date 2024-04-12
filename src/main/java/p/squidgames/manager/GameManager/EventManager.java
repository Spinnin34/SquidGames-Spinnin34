package p.squidgames.manager.GameManager;

import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import p.squidgames.SquidGames;

public class EventManager {
    public static void registerListener(Listener listener) {
        SquidGames.getInstance().getServer().getPluginManager().registerEvents(listener, SquidGames.getInstance());
    }
    public static void unregisterListener(Listener listener) {
        HandlerList.unregisterAll(listener);
    }
}