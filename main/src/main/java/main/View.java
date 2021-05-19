package main;
 

import javax.swing.*;
import java.awt.GridLayout;
public class View extends JPanel {
    public static final int CAVE_SIZE = 20;
    // on appelle JLabel au lieu de mettre des text dans les conteneur on met des tableau
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
        
        // setLayout affecter une nouvelle mise en page, a notre Container
        setLayout(new GridLayout(CAVE_SIZE,CAVE_SIZE));
        
        // on itialise notre labels avec 
        labels = new JLabel[CAVE_SIZE][CAVE_SIZE];
        
        //on construit  notre fenetre avec 20*20 case
        for (int x = 0; x < CAVE_SIZE; x++) {
            for (int y = 0; y < CAVE_SIZE; y++) {
                labels[x][y] = new JLabel(icons[cave[y][x]]);
                add(labels[x][y]);
            }     
        }
    }
    // on appel une autre fenetre avec les image
    public void updateCave(int[][] newCaveArray) {
        for (int x = 0; x < CAVE_SIZE; x++) {
            for (int y = 0; y < CAVE_SIZE; y++) {
                labels[x][y].setIcon(icons[newCaveArray[y][x]]);
            }
        }
    }
}