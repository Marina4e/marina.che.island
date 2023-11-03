package ua.javarush.basis;
import ua.javarush.entity.*;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;


public class Land extends JPanel {

    public final int size_x;
    public final int size_y;
    public final int cellSize;
    public final int toolbar_size_x;
    public JTextComponent text_console;
    public String event_text;
    public int organismQuantity;
    public int circle;
    public ArrayList<Organism> organisms;


    public void initialOrganisms() {
        new Wolf(this);
        new Fox(this);
        new Fox(this);

        new Sheep(this);
        new Sheep(this);
        new Sheep(this);

        new Boa(this);
        new Boa(this);
        new Boa(this);
        new Boa(this);

        new WolfBerries(this);
        new WolfBerries(this);
        new WolfBerries(this);
        new WolfBerries(this);
        new Grass(this);
        new Aloe(this);

        new Hare(this);
        new Hare(this);
        new Hare(this);
        new Hare(this);
        new Parrot(this);
        new Parrot(this);
        new Parrot(this);
        new Parrot(this);
    }

    public void drawTheWorld(Graphics g) {
        g.setColor(new Color(0xA0EDEDE9, true));
        for (int i = 0; i < size_x; i++) {
            for (int j = 0; j < size_y; j++)
                g.drawRect(i * cellSize, j * cellSize, cellSize, cellSize);
        }
        for (Organism organism : organisms) {
            organism.painting(g);
        }
    }

    public void eventTextFieldClear() {
        text_console.setText("");
        event_text = "";
    }

    public Land(int s_x, int s_y, int cellSize, JTextComponent text_console) {
        this.text_console = text_console;
        this.organisms = new ArrayList<>();
        this.organismQuantity = 0;
        this.circle = 0;
        this.size_x = s_x;
        this.size_y = s_y;
        this.cellSize = cellSize;
        this.toolbar_size_x = this.cellSize * (size_x / 3);
        setPreferredSize(new Dimension(size_x * this.cellSize + toolbar_size_x, size_y * this.cellSize));
        setFocusable(true);
        setBackground(Color.white);
        setLayout(null);
        initialOrganisms();
    }


    public void paint(Graphics g) {
        super.paint(g);
        drawTheWorld(g);
    }

    public void append(Organism organism) {
        if (organism == null)
            return;
        organisms.add(organism);
        organismQuantity++;
    }

    public void remove(Organism organism) {
        if (organism == null)
            return;
        organisms.remove(organism);
        organismQuantity--;
    }

    public Organism isFree(Organism organism) {
        for (int i = 0; i < organisms.size(); i++) {
            if (organism.overlap(organisms.get(i)) && (organisms.get(i) != organism)) {
                if (organism.getSpecies() == organisms.get(i).getSpecies())
                    return organism;
                return organisms.get(i);
            }
        }
        return null;
    }

    public void addEventText(String text) {
        this.event_text += text;
    }


    public void executeTour() {
        eventTextFieldClear();
        Collections.sort(organisms);
        for (int i = 0; i < organisms.size(); i++) {
            organisms.get(i).course();
        }
        for (int i = 0; i < organisms.size(); i++) {
            organisms.get(i).debugInfo();
        }
        circle++;
        text_console.setText(event_text);
        repaint();
    }

    public void spawnCommand() {
        new Wolf(this);
        repaint();
    }

    public int getXSize() {
        return size_x;
    }

    public int getYSize() {
        return size_y;
    }

    public int getCellSize() {
        return cellSize;
    }

    public int getToolbarX() {
        return toolbar_size_x;
    }

    public int getCircle() {
        return circle;
    }

}




