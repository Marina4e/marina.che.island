package animalLandSimulation.entities;

import animalLandSimulation.basis.Animal;
import animalLandSimulation.basis.Land;
import animalLandSimulation.basis.Organism;

import java.awt.*;

public class Hare extends Animal {
    private static final char icon = 'H';
    private static final int power = 2;
    private static final char initiative = 1;
    private static final String name = "Hare";

    private static final Color hareColor = new Color(0x415a77);
    private boolean lastMove;


    public Hare(int x, int y, Land land) {
        super(icon, hareColor, power, initiative, x, y, land);
        this.species = name;
        this.lastMove = false;
    }

    public Hare(Land land) {
        super(icon, hareColor, power, initiative, land);
        this.species = name;
        this.lastMove = false;
    }


    @Override
    public void course() {
        if (!lastMove) {
            super.course();
            lastMove = true;
        } else {
            land.addEventText("The hare recently moved, now remains in place\n");
            lastMove = false;
            age++;
        }
    }


    @Override
    public void spawn(int spawn_x, int spawn_y) {
        new Hare(spawn_x, spawn_y, land);
    }


    @Override
    public Organism collision(Organism another) {
        return null;
    }

}
