package animalLandSimulation.entities;

import animalLandSimulation.basis.Animal;
import animalLandSimulation.basis.Land;
import animalLandSimulation.basis.Organism;

import java.awt.*;

public class Fox extends Animal {
    private static final char icon = 'F';
    private static final int power = 3;
    private static final char initiative = 7;
    private static final String name = "Fox";
    private static final Color foxColor = new Color(0xCB5F06);


    @Override
    public void move() {
        int prev_x = x;
        int prev_y = y;
        int timeout = 0;

        Organism result;
        do {
            x = prev_x;
            y = prev_y;
            if (timeout > 0)
                land.addEventText("The fox sensed danger! Change of direction.\n");

            super.move();
            result = land.isFree(this);

            timeout++;
            if (timeout > 255) {
                x = prev_x;
                y = prev_y;
                age++;
                return;
            }
        }
        while ((result != null && result.getPower() > this.power) && canSpawn(2));
    }


    public Fox(int x, int y, Land land) {
        super(icon, foxColor, power, initiative, x, y, land);
        this.species = name;
    }


    public Fox(Land land) {
        super(icon, foxColor, power, initiative, land);
        this.species = name;
    }

    @Override
    public void spawn(int spawn_x, int spawn_y) {
        new Fox(spawn_x, spawn_y, land);
    }

    @Override
    public Organism collision(Organism another) {
        return null;
    }

}
