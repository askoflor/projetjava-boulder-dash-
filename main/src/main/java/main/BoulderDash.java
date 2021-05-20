//appel du package
package main;
 
import controller.GameModel;
import view.View;

// importation des different package
import javax.swing.*;

import controller.GameModel;

import java.awt.event.*;
import java.awt.BorderLayout;

public class BoulderDash extends JFrame implements KeyListener, ActionListener {
	//on etand ces classes
    private View view;
    private GameModel gameModel;
    private JPanel infoPanel;
    private JLabel diamonds;
    private JLabel steps;
    //on initialise un niveau
    private int currentLevel;
    // on initialise le niveau max
    public  static final int MAX_LEVELS = 5;
    
    // on appel un methode private 
    private BoulderDash(String title) {
    	// on appell la super variable global "title"
        super(title);
        //on initialise le currentLevel a 1
        currentLevel = 1;
        //on instentie gameModel 
        gameModel = new GameModel(getLevel(currentLevel));
        //on instentie view 
        view = new View(gameModel.getCaveArray());
        //on definie un gestionnaire de disponibliter de la fenetre 
        this.setLayout(new BorderLayout());
        // on fait une initiation de la classe button
        // cette partie gere tout la partie restart
        JButton click = new JButton("idiot click and start again");
        // on defimie les comportements de la methode click
        // et on la  on instruit le jeu a recommencer la partie 
        //addActionListener permet de dire rajoute une nouuvel  fenetre a  notre jeu   
        click.addActionListener(this);
        click.addKeyListener(this);
        click.setActionCommand("restart");
        //on appelle JPanel sur infoPanel et diamonds et  steps  
        infoPanel = new JPanel();
        //on implement les methodes des classes  gameModel
        diamonds = new JLabel(gameModel.getDiamonds());
        steps = new JLabel(gameModel.getSteps());
        // et la on fini implementation de ses methodes 
        
        //on ajoute la methode "diamonds et steps" a infoPanel " pour qu'il affiche a l'ecran
        //on rajoute les methodes diamonds de label
        infoPanel.add(diamonds);
        infoPanel.add(steps);
        //il affiche en position top les resultat de la classe GameModel de la methode getDiamonds() 
       this.add(infoPanel,BorderLayout.NORTH);
       
       
       // on place le button click en bas de notre fenetre
        this.add(click,BorderLayout.SOUTH);
        
        // on implemente tout le contenu de notre projet au centre  de la fentre
        this.add(view,BorderLayout.CENTER);
        
        
        // on ferme la fenetre courant
        this.setDefaultCloseOperation(3);
        
        // on empeche utilisateur de redimentionne image 
        this.setResizable(false);
    } 
    // fin de la methode private 
    
    
    // on commence le main 
    public static void main (String[] args) {
    	
    	// on appelle notre atribut BoulderDash plus haut 
        BoulderDash boulderDash = new BoulderDash("Boulder Dash");
        boulderDash.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // setLocation permt d'indiquer d'ou le jeu doit  ouvrire
        boulderDash.setLocation(700,320);
        
        //pack() permet de dimmentioner automatiquement la fenetre en fonction des element qu'il contient
        boulderDash.pack();
        
        // rend visible notre fenetre
        boulderDash.setVisible(true);
        
        //permet la planification de l'exécution d'une tâche pour une seule exécution ou pour une exécution répétée à intervalles réguliers
        Timer t;
        t = new Timer(750, boulderDash);
        t.start();
        
    }
    // on recupere les action du clavier avec un keyPressed(KeyEvent e) il renvoi le resultat de l'action dans "e"
    public void keyPressed(KeyEvent e) {
    	
    	// on recupere l'evenement et on recupere son code puis on l'associe a "e" et on l'attribut a key 
        int key = e.getKeyCode();
        
        // puis on vien faire des comparaison  sur le "key"
        if(key == KeyEvent.VK_DOWN) {
            keyDown();
        } else if(key == KeyEvent.VK_UP) {
            keyUp();
        } else if(key == KeyEvent.VK_LEFT) {
            keyLeft();
        } else if(key == KeyEvent.VK_RIGHT) {
            keyRight();
        } else if(key == KeyEvent.VK_R) {
        	
        	// on n'appelle de nouveau notre element 
        	// GameModel(getLevel(currentLevel)) recoit les position et GameModel l'implemente ..il renvoit deux valeur x et y 
            gameModel = new GameModel(getLevel(currentLevel));
            
            // on rajoute un nouveau element dans notre fenetre view
            view.updateCave(gameModel.getCaveArray());
            
            // on redefini affichage des element comme le nombre de diament collecter et le temps mise
            diamonds.setText(gameModel.getDiamonds());
            
            //on affiche le nombre de piere detruit
            steps.setText(gameModel.getSteps());
        }    
    }
       //on appelle les methode ecoute clavier
    public void keyReleased(KeyEvent e) {
    }
    public void keyTyped(KeyEvent e) {
    }
    
    //
    private void update() {
        view.updateCave(gameModel.getCaveArray());
        diamonds.setText(gameModel.getDiamonds());
        steps.setText(gameModel.getSteps());
        
        //on verifi si le niveau est complet
        if(gameModel.getLevelCompleted()) {
        	
        	// on compare avec le niveau max
            if(currentLevel == MAX_LEVELS) {
                JOptionPane.showMessageDialog(this, "god You finich the game.\nClick OK to return to level 1.");
                currentLevel = 1;
                gameModel = new GameModel(getLevel(currentLevel));
                view.updateCave(gameModel.getCaveArray());
                diamonds.setText(gameModel.getDiamonds());
                steps.setText(gameModel.getSteps());
            } else {
                JOptionPane.showMessageDialog(this, "yes man you have true level.\nClick OK to move on to the next one.");
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
                   {7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7},
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