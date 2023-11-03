package ua.javarush.thread;

import ua.javarush.basis.Land;
import ua.javarush.entity.Aloe;
import ua.javarush.entity.Grass;

import javax.swing.*;
import java.util.Random;

public class PlantGrowthThread extends Thread {
    private Land land;
    private volatile boolean running = false;
    public PlantGrowthThread(Land land) {
        this.land = land;
    }
    public void stopGrowth() {
        running = false;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (running) {
            int x = random.nextInt(land.getXSize());
            int y = random.nextInt(land.getYSize());
            int plantType = random.nextInt(1);

            Runnable plantRunnable = () -> {
                if (plantType == 0) {
                    new Grass(x, y, land);
                } else if (plantType == 1) {
                    new Aloe(x, y, land);
                }
            };
            SwingUtilities.invokeLater(plantRunnable);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public boolean isRunning() {
        return running;
    }

    public void startGrowth() {
        running = true;
    }
}
