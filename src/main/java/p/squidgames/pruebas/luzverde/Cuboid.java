package p.squidgames.pruebas.luzverde;


import org.bukkit.Location;

public class Cuboid {
    private final Location minPoint;
    private final Location maxPoint;

    public Cuboid(Location loc1, Location loc2) {
        this.minPoint = new Location(loc1.getWorld(),
                Math.min(loc1.getX(), loc2.getX()),
                Math.min(loc1.getY(), loc2.getY()),
                Math.min(loc1.getZ(), loc2.getZ()));

        this.maxPoint = new Location(loc1.getWorld(),
                Math.max(loc1.getX(), loc2.getX()),
                Math.max(loc1.getY(), loc2.getY()),
                Math.max(loc1.getZ(), loc2.getZ()));
    }

    public boolean isInside(Location location) {
        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();

        return x >= minPoint.getX() && x <= maxPoint.getX() &&
                y >= minPoint.getY() && y <= maxPoint.getY() &&
                z >= minPoint.getZ() && z <= maxPoint.getZ();
    }
}
