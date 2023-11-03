package ua.javarush.entity;

import ua.javarush.basis.Land;
import ua.javarush.basis.Organism;
import ua.javarush.basis.Plant;

import java.awt.*;

public class Grass extends Plant {
    private static final char icon = 'G';
    private static final int power = 0;
    private static final char initiative = 0;
    private static final String name = "Grass";
    private static final int GRASS_SPREAD_CHANCE = 85;
    private static final Color grassColor = new Color(0x584CEF23, true);

    public Grass(int x, int y, Land land) {
        super(icon, grassColor, power, initiative, x, y, land);
        this.species = name;
        this.SPREAD_CHANCE = GRASS_SPREAD_CHANCE;
    }

    public Grass(Land land) {
        super(icon, grassColor, power, initiative, land);
        this.species = name;
        this.SPREAD_CHANCE = GRASS_SPREAD_CHANCE;
    }

    @Override
    public void spawn(int spawn_x, int spawn_y) {
        new Grass(spawn_x, spawn_y, land);
    }

    @Override
    public Organism collision(Organism another) {
        return null;
    }
}
