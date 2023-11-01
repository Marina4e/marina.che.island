package animalLandSimulation.run;

import animalLandSimulation.basis.Land;
import animalLandSimulation.threads.PlantGrowthThread;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;


public class Main {

    public static void main(String[] args) {
        JFrame gameWindow = new JFrame("Animal island");

        JButton nextMove = new JButton("Next Move");
        JButton addWolf = new JButton("Add Wolf");
        JButton startPlantGrowthButton = new JButton("Start Fast Grass Growth");

        addWolf.setHorizontalTextPosition(SwingConstants.CENTER);
        addWolf.setBackground(new Color(0xBE23E5EF, true));

        nextMove.setHorizontalTextPosition(SwingConstants.CENTER);
        nextMove.setVerticalAlignment(SwingConstants.CENTER);
        nextMove.setBackground(new Color(0xB5B5B2));

        startPlantGrowthButton.setHorizontalTextPosition(SwingConstants.CENTER);
        startPlantGrowthButton.setBackground(new Color(0xBE23EF4C, true));

        final JTextComponent[] text_console = {new JTextPane()};
        JScrollPane EventPanel = new JScrollPane(text_console[0]);
        Land Area = new Land(30, 30, 25, text_console[0]);

        EventPanel.setBounds(Area.getXSize() * Area.getCellSize() + 10, 0, Area.getToolbarX() - 20, (Area.getYSize() / 2) * Area.getCellSize() - 20);

        nextMove.setBounds(Area.getXSize() * Area.getCellSize() + 10, (Area.getYSize() / 2) * Area.getCellSize(), Area.getToolbarX() - 20, 30);
        nextMove.addActionListener(e -> Area.executeTour());

        addWolf.setBounds(Area.getXSize() * Area.getCellSize() + 10, (Area.getYSize() / 2) * Area.getCellSize() + (4 * 50), Area.getToolbarX() - 20, 30);
        addWolf.addActionListener(e -> Area.spawnCommand());

        startPlantGrowthButton.setBounds(Area.getXSize() * Area.getCellSize() + 10, (Area.getYSize() / 2) * Area.getCellSize() + (2 * 50), Area.getToolbarX() - 20, 30);
        PlantGrowthThread plantGrowthThread = new PlantGrowthThread(Area);
        startPlantGrowthButton.addActionListener(e -> {
            if (!plantGrowthThread.isRunning()) {
                plantGrowthThread.startGrowth();
                Thread growthThread = new Thread(plantGrowthThread);
                growthThread.start();
            } else {
                plantGrowthThread.stopGrowth();
            }
        });

        Area.add(addWolf);
        Area.add(nextMove);
        Area.add(startPlantGrowthButton);
        Area.add(EventPanel);

        gameWindow.add(Area);
        gameWindow.pack();
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setResizable(false);
        gameWindow.setVisible(true);
    }
}
