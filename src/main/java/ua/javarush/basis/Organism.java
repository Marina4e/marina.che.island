package ua.javarush.basis;

import java.awt.*;
import java.util.Random;

public abstract class Organism implements Comparable<Organism> {
    public static final int ANIMAL = 0;
    public static final int PLANT = 1;
    public static final int LIST = 2;

    public char icon;
    public Color color;
    public String species;
    public int kingdom;

    public int power;
    public int initiative;
    public int x;
    public int y;
    public int age;

    public Land land;


    public Organism(char icon, Color color, int power, int initiative, int x, int y, Land land) {
        this.age = 0;
        this.color = color;
        this.icon = icon;
        this.power = power;
        this.initiative = initiative;
        this.x = x;
        this.y = y;
        this.land = land;
        this.land.append(this);
    }


    public Organism(char icon, Color color, int power, int initiative, Land land) {
        this.age = 0;
        this.color = color;
        this.icon = icon;
        this.power = power;
        this.initiative = initiative;
        Random random = new Random();
        x += random.nextInt(land.getXSize());
        y += random.nextInt(land.getYSize());
        this.land = land;
        this.land.append(this);
    }

    public boolean outOfWorld(int new_x, int new_y) {
        return (new_x >= land.getXSize() || new_y >= land.getYSize() || new_x < 0 || new_y < 0);
    }

    public void painting(Graphics g) {
        g.setColor(color);
        g.fillRect(x * land.getCellSize(), y * land.getCellSize(), land.getCellSize(), land.getCellSize());
        if (kingdom == ANIMAL) {
            g.setColor(Color.white);
            g.setFont(new Font("Arial", Font.PLAIN, land.getCellSize() - 8));
            g.drawString("" + icon, x * land.getCellSize() + 4, y * land.getCellSize() + land.getCellSize() - 8);
        }
    }

    public boolean overlap(Organism another) {
        return (this.x == another.x && this.y == another.y);
    }

    public boolean canSpawn(int organismType) {
        int prev_x = x;
        int prev_y = y;

        for (int i = -1; i <= 1; ++i) {
            for (int j = -1; j <= 1; ++j) {
                if (i == 0 && j == 0)
                    continue;
                x = prev_x + i;
                y = prev_y + j;

                Organism result = land.isFree(this);

                switch (organismType) {
                    case PLANT -> {
                        if (result == null && !outOfWorld(x, y)) {
                            x = prev_x;
                            y = prev_y;
                            return true;
                        }
                    }
                    case ANIMAL -> {
                        if ((result == null && !outOfWorld(x, y)) || (result != null && result.getKingdom() == 1 && !outOfWorld(x, y))) {
                            x = prev_x;
                            y = prev_y;
                            return true;
                        }
                    }
                    case LIST -> {
                        if ((result == null && !outOfWorld(x, y)) || (result != null && result.getPower() < this.power && !outOfWorld(x, y))) {
                            x = prev_x;
                            y = prev_y;
                            return true;
                        }
                    }
                }
            }
        }
        x = prev_x;
        y = prev_y;
        return false;
    }

    public Organism fight(Organism another) {
        if (this.power > another.power || (this.power == another.power && this.age > another.age))
            return another;
        else
            return this;
    }


    public void debugInfo() {
        land.addEventText("[" + species + "]" + " P: " + power + " I: " + initiative + " POS (x: " + x + " y: " + y + " )\n");
    }


    @Override
    public int compareTo(Organism another) {
        if (initiative == another.initiative) {
            if (age == another.age)
                return 0;
            return age > another.age ? -1 : 1;
        }

        return initiative > another.initiative ? -1 : 1;
    }


    public void collision(int organismType) {
        int prevX = x;
        int prevY = y;
        int timeout = 0;
        Random random = new Random();
        do {
            x = prevX + random.nextInt(3) - 1;
            y = prevY + random.nextInt(3) - 1;

            if (timeout++ > 255) {
                System.out.println("x=" + x + " y=" + y + "canSpawn: " + canSpawn(0) + "\n");
                x = prevX;
                y = prevY;
                return;
            }
        }
        while (outOfWorld(x, y) || (x == prevX && y == prevY) || (organismType == 1 ? land.isFree(this) != null : land.isFree(this) != null && land.isFree(this).getKingdom() == 0));
        land.addEventText(organismType == 1 ? species + " it spreads\n" : "A new animal was born " + species + "[" + x + "," + y + "]\n");
        spawn(x, y);
        x = prevX;
        y = prevY;
    }

    public void fixReference(Land land) {
        this.land = land;
    }

    public String getSpecies() {
        return species;
    }

    public int getPower() {
        return power;
    }

    public int getKingdom() {
        return kingdom;
    }

    public void strengthIncrease(int value) {
        power += value;
    }

    public abstract void spawn(int spawn_x, int spawn_y);

    public abstract void course();

    public abstract Organism collision(Organism organism);
}
