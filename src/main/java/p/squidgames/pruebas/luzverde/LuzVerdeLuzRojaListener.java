package p.squidgames.pruebas.luzverde;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import p.squidgames.SquidGames;

public class LuzVerdeLuzRojaListener implements Listener {
    private Cuboid gameRegion;
    private Cuboid metaRegion;
    private boolean gameRunning;
    private boolean isEnabled;
    private SquidGames plugin;

    private int greenLightDuration = 20 * 10; // 10 seconds (in ticks)
    private int redLightDuration = 20 * 5;    // 5 seconds (in ticks)
    private int interval = greenLightDuration + redLightDuration;


    public void enable() {
        isEnabled = true;
        startLightCycle();
    }

    public void disable() {
        isEnabled = false;
    }

    public LuzVerdeLuzRojaListener(SquidGames plugin) {
        this.plugin = plugin;
        World world = plugin.getServer().getWorld("world");
        if (world == null) {
            plugin.getLogger().severe("No se pudo cargar el mundo. El plugin no funcionará correctamente.");
            return;
        }

        Location start1 = new Location(world, 151, -8, -220);
        Location end1 = new Location(world, 79, -48, -314);
        gameRegion = new Cuboid(start1, end1);

        Location start2 = new Location(world, 79, -49, -315);
        Location end2 = new Location(world, 151, 1, -329);
        metaRegion = new Cuboid(start2, end2);


        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    private void startLightCycle() {
        new BukkitRunnable() {
            boolean greenLight = true;

            @Override
            public void run() {
                if (!isEnabled) {
                    cancel();
                    return;
                }

                if (greenLight) {
                    gameRunning = true;
                    broadcastGreenLight();
                    greenLight = false;
                    getLightCycleTask().runTaskLater(plugin, greenLightDuration);
                } else {
                    gameRunning = false;
                    broadcastRedLight();
                    greenLight = true;
                    getLightCycleTask().runTaskLater(plugin, redLightDuration);
                }
            }
        }.runTaskTimer(plugin, 0, interval);
    }
    private BukkitRunnable lightCycleTask;
    // Otros campos y métodos


    private BukkitRunnable getLightCycleTask() {
        return lightCycleTask;
    }



    private void broadcastGreenLight() {
        Plugin plugin = SquidGames.getInstance();
        plugin.getServer().broadcastMessage("¡Luz Verde!");
    }

    private void broadcastRedLight() {
        Plugin plugin = SquidGames.getInstance();
        plugin.getServer().broadcastMessage("¡Luz Roja!");
    }


    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (!isEnabled || !gameRunning) return;

        Player player = event.getPlayer();
        Location to = event.getTo();

        if (!gameRegion.isInside(to)) {
            player.teleport(player.getWorld().getSpawnLocation());
            player.sendMessage("¡Has sido eliminado del juego!");
        }
    }
}


