package p.squidgames.utils.task;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import p.squidgames.SquidGames;

import java.util.function.Consumer;

@Accessors(chain = true)
public class RepeatableTask {
    @Getter private static int bukkitTaskId = 0;

    @Getter @Setter private Runnable end;
    @Getter @Setter private int repetitions;

    public RepeatableTask(Consumer<Integer> consumer, int start, int delay, int repetitions) {
        this.repetitions = repetitions;

        bukkitTaskId = SquidGames.getInstance().getServer().getScheduler().scheduleSyncRepeatingTask(SquidGames.getInstance(), () -> {
            consumer.accept(this.repetitions);
            this.repetitions--;

            if (repetitions <= 0) {
                SquidGames.getInstance().getServer().getScheduler().cancelTask(bukkitTaskId);
                this.end.run();
            }
        }, start, delay);
    }
}
