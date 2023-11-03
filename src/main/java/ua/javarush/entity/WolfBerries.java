package ua.javarush.entity;

import ua.javarush.basis.Land;
import ua.javarush.basis.Organism;
import ua.javarush.basis.Plant;

import java.awt.*;

public class WolfBerries extends Plant {
    private static final char icon = 'C';
    private static final int power = 0;
    private static final char initiative = 0;
    private static final String name = "Wolf Berries";
    private static final int WB_SPREAD_CHANCE = 98;
    private static final Color wbColor = new Color(0xD0B60064, true);


    public WolfBerries(int x, int y, Land land) {
        super(icon, wbColor, power, initiative, x, y, land);
        this.species = name;
        this.SPREAD_CHANCE = WB_SPREAD_CHANCE;
    }

    public WolfBerries(Land land) {
        super(icon, wbColor, power, initiative, land);
        this.species = name;
        this.SPREAD_CHANCE = WB_SPREAD_CHANCE;
    }

    @Override
    public void spawn(int spawn_x, int spawn_y) {
        new WolfBerries(spawn_x, spawn_y, land);
    }

    @Override
    public Organism collision(Organism another) {
        if (another == null) return null;
        land.addEventText(another.getSpecies() + " ate WolfBerries and died\n");
        land.remove(this);
        return another;
    }
}
