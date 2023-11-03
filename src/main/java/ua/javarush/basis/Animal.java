package ua.javarush.basis;

import java.awt.*;
import java.util.Random;

public abstract class Animal extends Organism {
    public static final int MINIMAL_AGE_TO_REPRODUCE = 10;

    public Animal(char icon, Color color, int power, int initiative, int x, int y, Land land) {
        super(icon, color, power, initiative, x, y, land);
        kingdom = ANIMAL;
    }

    public Animal(char icon, Color color, int power, int initiative, Land land) {
        super(icon, color, power, initiative, land);
        kingdom = ANIMAL;
    }


    public void move() {
        Random random = new Random();
        int move_x;
        int move_y;
        do {
            move_x = random.nextInt(3) - 1;
            move_y = random.nextInt(3) - 1;

        } while (outOfWorld(x + move_x, y + move_y) ||
                (move_x == 0 && move_y == 0));

        x += move_x;
        y += move_y;
    }


    @Override
    public void course() {
        land.addEventText(species + " is moving\n");

        int prev_x = x;
        int prev_y = y;

        move();
        Organism result = land.isFree(this);

        if (result == this && age > MINIMAL_AGE_TO_REPRODUCE) {
            x = prev_x;
            y = prev_y;

            if (canSpawn(ANIMAL))
                collision(ANIMAL);
            else
                land.addEventText("A small animal " + species + " cannot come into the world. Not enough space!\n");
        }

        if (result != null && result != this) {
            Organism temp = result.collision(this);

            if (fight(result) == this || temp == this) {
                land.addEventText(result.getSpecies() + " ate " + species + "\n");
                land.remove(this);
                return;
            } else if (temp == result) {
                land.addEventText(result.getSpecies() + " repulsed the attack " + species + "\n");
                x = prev_x;
                y = prev_y;
            } else {
                land.remove(result);
                land.addEventText(species + " ate " + result.getSpecies() + "\n");
            }
        }
        age++;
    }

    @Override
    public abstract void spawn(int spawn_x, int spawn_y);

    @Override
    public abstract Organism collision(Organism organism);
}
