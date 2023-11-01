package animalLandSimulation.entities;

import animalLandSimulation.basis.Land;
import animalLandSimulation.basis.Organism;
import animalLandSimulation.basis.Plant;

import java.awt.*;

public class Aloe extends Plant {
    public static final char icon = 'A';
    public static final int power = 0;
    public static final int increase = 3;
    public static final char initiative = 0;
    public static final String name = "Aloe";
    public static final int ALOE_SPREAD_CHANCE = 91;
    public static final Color aloeColor = new Color(0x8B60EF23, true);


    public Aloe(int x, int y, Land land) {
        super(icon, aloeColor, power, initiative, x, y, land);
        this.species = name;
        this.SPREAD_CHANCE = ALOE_SPREAD_CHANCE;
    }

    public Aloe(Land land) {
        super(icon, aloeColor, power, initiative, land);
        this.species = name;
        this.SPREAD_CHANCE = ALOE_SPREAD_CHANCE;
    }


    @Override
    public void spawn(int spawn_x, int spawn_y) {
        new Aloe(spawn_x, spawn_y, land);
    }


    @Override
    public Organism collision(Organism another) {
        if (another == null) return null;

        land.addEventText(another.getSpecies() + " increases it's strength by " + increase + "\n");
        another.strengthIncrease(increase);

        return null;
    }
}
