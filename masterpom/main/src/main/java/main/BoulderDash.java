package main;
 

import javax.swing.*;
import java.awt.event.*;
import java.awt.BorderLayout;

public class BoulderDash extends JFrame implements KeyListener, ActionListener {
    private View view;
    private GameModel gameModel;
    private JPanel infoPanel;
    private JLabel diamonds;
    private JLabel steps;
    private int currentLevel;
    public  static final int MAX_LEVELS = 5;
    private BoulderDash(String title) {
        super(title);
        currentLevel = 1;
        gameModel = new GameModel(getLevel(currentLevel));
        view = new View(gameModel.getCaveArray());
        this.setLayout(new BorderLayout());
        JButton click = new JButton("Restart Current Level");
        click.addActionListener(this);
        click.addKeyListener(this);
        click.setActionCommand("restart");
        infoPanel = new JPanel();
        diamonds = new JLabel(gameModel.getDiamonds());
        steps = new JLabel(gameModel.getSteps());
        infoPanel.add(diamonds);
        infoPanel.add(steps);
        this.add(infoPanel,BorderLayout.NORTH);
        this.add(click,BorderLayout.SOUTH);
        this.add(view,BorderLayout.CENTER);
        this.setDefaultCloseOperation(3);
        this.setResizable(false);
    }
    public static void main (String[] args) {
        BoulderDash boulderDash = new BoulderDash("Boulder Dash");
        boulderDash.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        boulderDash.setLocation(700,320);
        boulderDash.pack();
        boulderDash.setVisible(true);
        Timer t;
        t = new Timer(750, boulderDash);
        t.start();
    }
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_DOWN) {
            keyDown();
        } else if(key == KeyEvent.VK_UP) {
            keyUp();
        } else if(key == KeyEvent.VK_LEFT) {
            keyLeft();
        } else if(key == KeyEvent.VK_RIGHT) {
            keyRight();
        } else if(key == KeyEvent.VK_R) {
            gameModel = new GameModel(getLevel(currentLevel));
            view.updateCave(gameModel.getCaveArray());
            diamonds.setText(gameModel.getDiamonds());
            steps.setText(gameModel.getSteps());
        }    
    }
    public void keyReleased(KeyEvent e) {
    }
    public void keyTyped(KeyEvent e) {
    }
    private void update() {
        view.updateCave(gameModel.getCaveArray());
        diamonds.setText(gameModel.getDiamonds());
        steps.setText(gameModel.getSteps());
        if(gameModel.getLevelCompleted()) {
            if(currentLevel == MAX_LEVELS) {
                JOptionPane.showMessageDialog(this, "Congratulations! You've completed the game.\nClick OK to return to level 1.");
                currentLevel = 1;
                gameModel = new GameModel(getLevel(currentLevel));
                view.updateCave(gameModel.getCaveArray());
                diamonds.setText(gameModel.getDiamonds());
                steps.setText(gameModel.getSteps());
            } else {
                JOptionPane.showMessageDialog(this, "Congratulations! You've completed the level.\nClick OK to move on to the next one.");
                currentLevel++;
                gameModel = new GameModel(getLevel(currentLevel));
                view.updateCave(gameModel.getCaveArray());
                diamonds.setText(gameModel.getDiamonds());
                steps.setText(gameModel.getSteps());
            }
        }
    }
    private void keyUp() {
        gameModel.moveUp();
        update();
    }
    private void keyDown() {
        gameModel.moveDown();
        update();
    }
    private void keyLeft() {
        gameModel.moveLeft();
        update();
    }
    private void keyRight() {
        gameModel.moveRight();
        update();
    }
    public void actionPerformed (ActionEvent e) {
        if("restart".equals(e.getActionCommand())) {
            gameModel = new GameModel(getLevel(currentLevel));
            view.updateCave(gameModel.getCaveArray());
            diamonds.setText(gameModel.getDiamonds());
            steps.setText(gameModel.getSteps());
        }
        gameModel.tick();
        view.updateCave(gameModel.getCaveArray());
        if(gameModel.getGameOver()) {
            JOptionPane.showMessageDialog(this, "Oh no, Rockford has been crushed!\nClick OK to restart the level.");
            gameModel = new GameModel(getLevel(currentLevel));
            view.updateCave(gameModel.getCaveArray());
            diamonds.setText(gameModel.getDiamonds());
            steps.setText(gameModel.getSteps());
        }
    }
    public int[][] getLevel(int levelNumber) {
        int[][] level0 = {{7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7},  
        		   {7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7}, 
        		   {7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7},
                   {7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7},
                   {7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7},
                   {7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7},
                   {7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7},
                   {7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7},
                   {7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7},
                   {1,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7},
                   {7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7},
                   {7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7},
                   {7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7},
                   {7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7},
                   {7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7},
                   {7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7},
                   {7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7},
                   {7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7},
                   {7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7},
                   {7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7}};
        int[][] level1 = {{8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
                   {8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
                   {8,8,3,3,3,3,3,3,3,3,3,3,3,4,3,3,3,3,8,8},
                   {1,7,7,7,3,3,4,3,3,3,3,3,3,3,3,3,3,3,8,8},
                   {8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,3,4,3,8,8},
                   {8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,3,3,3,8,8},
                   {8,8,4,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,8,8},
                   {8,8,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,8,8},
                   {8,8,3,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
                   {8,8,3,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
                   {8,8,3,3,8,3,3,3,3,3,3,3,3,3,3,3,3,3,8,8},
                   {8,8,4,3,3,3,3,3,3,3,3,3,3,3,3,3,4,3,8,8},
                   {8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,3,4,3,8,8},
                   {8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,3,4,3,8,8},
                   {8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,3,3,3,8,8},
                   {8,8,3,3,3,4,3,3,3,3,3,3,3,3,3,3,3,3,8,8},
                   {5,7,3,3,4,4,4,3,3,3,3,3,3,3,3,3,3,3,8,8},
                   {8,8,3,3,3,4,3,3,3,3,3,3,3,3,3,3,3,3,8,8},
                   {8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
                   {8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8}};
        int[][] level2 = {{8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
                    {8,8,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,8},
                    {8,8,7,7,7,7,7,0,7,7,7,7,7,7,7,7,7,7,7,1},
                    {8,8,7,7,7,7,0,4,8,8,8,8,8,8,8,8,8,8,8,8},
                    {8,8,3,3,7,4,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
                    {8,8,8,8,3,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
                    {8,8,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,8,8},
                    {8,3,3,3,3,3,3,3,0,3,0,3,3,3,3,3,3,3,8,8},
                    {8,8,8,8,8,8,8,8,4,8,4,8,8,8,3,3,3,3,8,8},
                    {8,8,8,8,8,8,8,8,8,8,8,8,8,8,3,3,3,3,8,8},
                    {8,8,8,8,8,8,8,8,8,8,8,8,8,8,3,3,3,3,8,8},
                    {8,3,3,3,3,3,3,3,3,3,0,3,3,3,3,3,3,3,8,8},
                    {8,3,3,3,3,3,3,3,8,8,0,3,3,3,3,3,3,3,8,8},
                    {8,3,3,3,3,3,3,3,8,8,0,3,3,3,3,3,3,3,8,8},
                    {8,3,3,3,4,3,3,3,8,8,8,8,8,3,3,3,4,3,8,8},
                    {8,3,3,4,4,4,3,3,8,8,8,8,8,3,3,3,4,3,8,8},
                    {8,3,4,4,4,4,4,3,8,8,0,3,3,3,3,3,3,3,8,8},
                    {8,3,3,3,3,3,3,3,8,8,4,3,3,3,3,3,3,3,8,8},  
                    {8,3,3,3,3,3,3,3,8,8,3,3,3,3,3,3,3,3,8,8},
                    {8,8,8,8,8,8,8,8,8,8,8,8,8,8,5,8,8,8,8,8}};
        int[][] level3 = {{8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
                    {8,8,8,8,8,8,8,8,8,1,8,8,8,8,8,8,8,8,8,8},
                    {8,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,8,8},
                    {8,3,3,3,3,3,3,3,3,3,0,3,3,3,3,3,4,3,8,8},
                    {8,3,3,3,3,3,8,8,8,8,3,8,8,8,8,8,8,8,8,8},
                    {8,3,3,3,3,3,8,3,3,3,3,3,3,3,8,3,8,8,8,8},
                    {8,3,3,3,3,3,8,3,3,3,3,3,3,3,8,3,8,8,8,8},
                    {8,3,3,3,3,3,8,3,3,4,4,4,3,3,8,3,8,8,8,8},
                    {8,3,3,3,3,3,8,3,3,4,4,4,3,3,8,3,8,0,8,8},
                    {8,3,3,3,3,3,8,3,3,3,3,3,3,3,8,3,3,4,8,8},
                    {8,3,3,4,3,3,8,3,3,3,3,3,3,0,3,3,3,3,8,8},
                    {8,3,4,4,4,3,8,3,3,3,3,3,3,3,8,3,3,3,8,8},
                    {8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,3,3,3,8,8},
                    {8,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,4,4,8},
                    {8,3,3,3,3,3,3,3,3,0,3,3,3,3,3,3,3,3,8,8},
                    {8,3,3,4,3,3,3,3,3,3,3,3,3,3,3,3,3,3,8,8},
                    {8,3,3,3,3,3,3,8,8,3,8,8,3,3,3,3,3,3,8,8},
                    {8,3,3,3,3,3,8,8,3,3,3,8,8,3,3,3,3,3,8,8},
                    {8,8,8,8,8,8,8,8,3,5,3,8,8,8,8,8,8,8,8,8},
                    {8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8}};
        int[][] level4 = {{8,8,1,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
                   {8,3,3,3,3,8,8,3,3,3,3,3,3,3,3,3,3,3,3,8},
                   {8,3,3,3,3,8,4,0,3,3,3,3,3,3,3,3,3,3,3,8},
                   {8,3,3,3,3,8,8,3,3,3,3,3,3,4,3,3,3,3,3,8},
                   {8,3,3,3,3,8,4,0,3,3,3,3,4,4,4,3,3,3,3,8},
                   {8,3,3,3,3,8,8,3,3,3,3,3,3,4,3,3,3,3,3,8},
                   {8,3,3,3,3,8,8,3,3,3,3,3,3,3,3,3,3,3,3,8},
                   {8,3,3,3,3,8,8,0,3,3,3,3,3,3,3,3,3,3,3,8},
                   {8,3,3,3,3,8,8,0,3,3,3,3,3,3,3,3,3,3,3,8},
                   {8,3,3,3,3,8,8,0,3,3,3,3,3,3,0,3,0,3,3,8},
                   {8,3,3,3,3,8,8,3,3,3,3,3,3,3,3,3,3,3,3,8},
                   {8,3,3,3,3,3,3,3,8,8,8,8,8,8,4,8,4,8,8,8},
                   {8,3,3,3,3,3,3,3,8,8,8,8,8,8,8,8,8,8,8,8},
                   {8,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,8},
                   {8,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,8},
                   {8,3,3,4,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,8},
                   {8,3,3,4,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,8},
                   {8,3,3,4,4,4,4,3,3,3,3,3,3,3,3,3,3,3,3,8},
                   {8,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,8},
                   {8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,5,8,8,8,8}};
        int[][] level5 = {{8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},  
                   {8,8,0,4,4,4,0,8,8,8,8,8,8,8,8,8,4,3,4,8}, 
                   {1,7,4,0,0,0,4,3,3,3,3,3,3,3,3,3,3,3,0,8},
                   {8,8,3,3,3,3,3,8,8,8,8,8,8,8,8,8,8,3,0,8},
                   {8,8,8,8,7,8,8,8,8,4,8,8,8,8,8,8,3,3,8,8},
                   {8,8,3,3,3,3,3,8,8,0,8,8,8,8,8,3,3,0,8,8},
                   {8,8,3,3,3,3,3,8,8,4,8,8,8,8,3,3,0,8,8,8},
                   {8,8,3,3,4,3,3,8,8,0,8,0,8,3,3,0,8,8,8,8},
                   {8,8,3,3,3,3,3,8,8,3,3,3,3,3,0,8,8,8,8,8},
                   {8,8,3,3,3,3,3,8,8,3,3,3,8,7,8,8,8,8,8,8},
                   {8,8,0,0,0,0,0,8,8,3,3,3,8,7,8,8,8,8,8,8,8},
                   {8,8,3,3,3,3,3,3,3,3,8,8,8,7,8,8,8,8,8,8},
                   {8,8,3,3,3,3,3,8,8,3,8,8,8,7,8,8,3,4,3,8},
                   {8,8,3,3,3,3,3,8,8,3,8,8,4,7,4,8,4,4,4,8},
                   {8,8,3,3,3,3,3,8,8,8,8,8,4,0,4,8,3,4,3,8},
                   {8,8,3,3,3,3,3,8,8,8,4,8,8,8,8,8,8,3,8,8},
                   {8,8,3,3,3,3,3,8,0,0,4,0,0,8,8,8,8,0,8,8},
                   {8,8,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,8},
                   {8,8,4,3,3,3,4,8,8,8,8,8,8,8,8,3,8,7,8,8},
                   {8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,5,8,8,8,8}};
        if(levelNumber == 1) {
            return level1;
        } else if(levelNumber == 2) {
            return level2;
        } else if(levelNumber == 3) {
            return level3;
        } else if(levelNumber == 4) {
            return level4;
        } else if(levelNumber == 5) {
            return level5;
        } else {
            return level0;
        }
    } 
    
}