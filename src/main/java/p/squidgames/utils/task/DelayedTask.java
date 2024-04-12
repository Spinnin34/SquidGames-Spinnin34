package p.squidgames.utils.task;

import p.squidgames.SquidGames;

public class DelayedTask {
    public DelayedTask(Runnable runnable, int delay) {
        SquidGames.getInstance().getServer().getScheduler().scheduleSyncDelayedTask(SquidGames.getInstance(), runnable, delay);
    }
}
