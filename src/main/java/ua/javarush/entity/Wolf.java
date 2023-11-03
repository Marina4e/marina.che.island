package ua.javarush.entity;

import ua.javarush.basis.Animal;
import ua.javarush.basis.Land;
import ua.javarush.basis.Organism;

import java.awt.*;

public class Wolf extends Animal {
    private static final char icon = 'W';
    private static final int power = 9;
    private static final char initiative = 5;
    private static final String name = "Wolf";
    private static final Color wolfColor = new Color(0x353535);


    public Wolf(int x, int y, Land land) {
        super(icon, wolfColor, power, initiative, x, y, land);
        this.species = name;
    }

    public Wolf(Land land) {
        super(icon, wolfColor, power, initiative, land);
        this.species = name;
    }

    @Override
    public void spawn(int spawn_x, int spawn_y) {
        new Wolf(spawn_x, spawn_y, land);
    }

    @Override
    public Organism collision(Organism another) {
        return null;
    }

}
