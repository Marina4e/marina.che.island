package animalLandSimulation.basis;

import java.awt.*;
import java.util.Random;

public abstract class Plant extends Organism {
    public int SPREAD_CHANCE;

    public Plant(char icon, Color color, int power, int initiative, int x, int y, Land land) {
        super(icon, color, power, initiative, x, y, land);
        kingdom = PLANT;
    }

    public Plant(char icon, Color color, int power, int initiative, Land land) {
        super(icon, color, power, initiative, land);
        kingdom = PLANT;
    }

    @Override
    public void course() {
        if (age == 0 && land.getCircle() > 1) {
            age++;
            return;
        }
        Random random = new Random();
        if (random.nextInt(100) > SPREAD_CHANCE && canSpawn(PLANT))
            collision(PLANT);
        age++;
    }

    @Override
    public abstract void spawn(int spawn_x, int spawn_y);

    @Override
    public abstract Organism collision(Organism another);
}
