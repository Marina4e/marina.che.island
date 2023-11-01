package animalLandSimulation.entities;

import animalLandSimulation.basis.Animal;
import animalLandSimulation.basis.Land;
import animalLandSimulation.basis.Organism;

import java.awt.*;

public class Sheep extends Animal {
    private static final char icon = 'S';
    private static final int power = 4;
    private static final char initiative = 4;
    private static final String name = "Sheep";
    private static final Color sheepColor = new Color(0xA14781FF, true);


    public Sheep(int x, int y, Land land) {
        super(icon, sheepColor, power, initiative, x, y, land);
        this.species = name;
    }

    public Sheep(Land land) {
        super(icon, sheepColor, power, initiative, land);
        this.species = name;
    }

    @Override
    public void spawn(int spawn_x, int spawn_y) {
        new Sheep(spawn_x, spawn_y, land);
    }


    @Override
    public Organism collision(Organism another) {
        return null;
    }
}
