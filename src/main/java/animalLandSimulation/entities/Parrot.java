package animalLandSimulation.entities;

import animalLandSimulation.basis.Animal;
import animalLandSimulation.basis.Land;
import animalLandSimulation.basis.Organism;

import java.awt.*;

public class Parrot extends Animal {
    private static final char icon = 'P';
    private static final int power = 1;
    private static final char initiative = 8;
    private static final String name = "Parrot";
    private static final Color parrotColor = new Color(0x4A47FF);


    public Parrot(int x, int y, Land land) {
        super(icon, parrotColor, power, initiative, x, y, land);
        this.species = name;
    }

    public Parrot(Land land) {
        super(icon, parrotColor, power, initiative, land);
        this.species = name;
    }

    @Override
    public void spawn(int spawn_x, int spawn_y) {
        new Parrot(spawn_x, spawn_y, land);
    }

    @Override
    public Organism collision(Organism another) {
        return null;
    }

}
