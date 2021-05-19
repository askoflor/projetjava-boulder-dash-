package main;
 

import javax.swing.*;
import java.awt.GridLayout;
public class View extends JPanel {
    public static final int CAVE_SIZE = 20;
    private JLabel[][] labels;
    private Icon[] icons;
    public View(int[][] cave) {
        icons = new Icon[9];
        icons[0] = new ImageIcon("./images/stone.png");
        icons[1] = new ImageIcon("./images/Gauche.png");
        icons[3] = new ImageIcon("./images/ground.png");
        icons[4] = new ImageIcon("./images/diamond.png");
        icons[5] = new ImageIcon("./images/kk.png");
        icons[6] = new ImageIcon("./images/ll.png");
        icons[7] = new ImageIcon("./images/voidGround.png");
        icons[8] = new ImageIcon("./images/unbreakableWall.png");
        setLayout(new GridLayout(CAVE_SIZE,CAVE_SIZE));
        labels = new JLabel[CAVE_SIZE][CAVE_SIZE];
        for (int x = 0; x < CAVE_SIZE; x++) {
            for (int y = 0; y < CAVE_SIZE; y++) {
                labels[x][y] = new JLabel(icons[cave[y][x]]);
                add(labels[x][y]);
            }     
        }
    }
    public void updateCave(int[][] newCaveArray) {
        for (int x = 0; x < CAVE_SIZE; x++) {
            for (int y = 0; y < CAVE_SIZE; y++) {
                labels[x][y].setIcon(icons[newCaveArray[y][x]]);
            }
        }
    }
}