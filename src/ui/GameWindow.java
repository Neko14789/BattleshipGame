package ui;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    private final Dimension playfieldSize = new Dimension(10,10);
    private PlayfieldPanel playfieldPanel;
    GameWindow(Dimension size, String title) {
        setupComponents();
        setupListeners();
        setupUI();


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(size);
        setResizable(true);
        setLocationRelativeTo(null);

        setLayout(new FlowLayout());

        setTitle(title);




        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/assets/BattleshipIcon256x256.png")));
        setVisible(true);

    }

    private void setupComponents(){
        playfieldPanel = new PlayfieldPanel(playfieldSize, "Player 1");
    }
    private void setupListeners(){

    }
    private void setupUI(){
        add(playfieldPanel);
    }


    public static void main(String[] args) {
        GameWindow gameWindow = new GameWindow(new Dimension(1920,1080), "Battleship Game");
    }
}
