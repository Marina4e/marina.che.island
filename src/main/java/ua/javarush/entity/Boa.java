package ua.javarush.entity;

import ua.javarush.basis.Animal;
import ua.javarush.basis.Land;
import ua.javarush.basis.Organism;

import java.awt.*;
import java.util.Random;

public class Boa extends Animal {

    private static final char icon = 'B';
    private static final int power = 2;
    private static final char initiative = 1;
    private static final String name = "Boa";
    private static final Color boaColor = new Color(0xFFFFCE47, true);
    private static final int CHANCE_TO_STAY = 75;
    private static final int defend = 5;


    public Boa(int x, int y, Land land) {
        super(icon, boaColor, power, initiative, x, y, land);
        this.species = name;
    }

    public Boa(Land land) {
        super(icon, boaColor, power, initiative, land);
        this.species = name;
    }


    @Override
    public void course() {
        Random random = new Random();

        if (random.nextInt(100) > CHANCE_TO_STAY)
            super.course();
        else {
            land.addEventText("Boa does not move anywhere\n");
            age++;
        }
    }


    @Override
    public void spawn(int spawn_x, int spawn_y) {
        new Boa(spawn_x, spawn_y, land);
    }


    @Override
    public Organism collision(Organism another) {
        if (another.getPower() < defend)
            return this;

        return null;
    }
}
