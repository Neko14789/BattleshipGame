package com.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ResourceBundle;

public class GameWindow extends JFrame {
    private final Dimension playfieldSize = new Dimension(10,10);

    //menu
    private JMenuBar mainMenuBar;
    private JMenu gameMenu;
    private JMenuItem hostGameMenuItem;
    private JMenuItem joinGameMenuItem;
    private JMenuItem quitGameMenuItem;
    private JMenu helpMenu;
    private JMenuItem aboutMenuItem;
    private JMenuItem changeSettingsMenuItem;


    private PlayfieldPanel playfieldPanel1;
    private PlayfieldPanel playfieldPanel2;
    private static ResourceBundle i18n;

    GameWindow(Dimension size) {
        setLayout(new GridBagLayout());

        setupComponents();
        setupListeners();
        setupUI();
        setupLanguage();
        setupTextOfComponents();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(size);
        setResizable(true);
        setLocationRelativeTo(null);

        setupDevMode();


        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/com/assets/BattleshipIcon256x256.png")));
        setVisible(true);
    }

    private void setupDevMode() {
        KeyboardFocusManager keyManager;

        keyManager=KeyboardFocusManager.getCurrentKeyboardFocusManager();
        keyManager.addKeyEventDispatcher(new KeyEventDispatcher() {
            public boolean dispatchKeyEvent(KeyEvent e) {
                if(e.getID() == KeyEvent.KEY_PRESSED && e.getKeyCode()=='D' && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0) && ((e.getModifiers() & KeyEvent.ALT_MASK) != 0) && ((e.getModifiers() & KeyEvent.SHIFT_MASK) != 0) ){
                    System.out.println("Dev Mode Activated");
                    JOptionPane.showMessageDialog(GameWindow.this, "Dev Mode Activated", "Dev Mode", JOptionPane.INFORMATION_MESSAGE);
                    return true;
                }
                return false;
            }

        });
    }

    private void setupComponents() {
        playfieldPanel1 = new PlayfieldPanel(playfieldSize, "Player 1");
        playfieldPanel2 = new PlayfieldPanel(playfieldSize, "Player 2");

        mainMenuBar = new JMenuBar();
        gameMenu = new JMenu();
            hostGameMenuItem = new JMenuItem();
            joinGameMenuItem = new JMenuItem();
            quitGameMenuItem = new JMenuItem();
        helpMenu = new JMenu();
        aboutMenuItem = new JMenuItem();
        changeSettingsMenuItem = new JMenuItem();
    }
    private void setupListeners(){

        //ONLY A TEST!!
        changeSettingsMenuItem.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playfieldPanel1.setPlayfieldSize(new Dimension(9,9));
                revalidate();
            }
        });
    }
    private void setupUI(){
        GridBagConstraints c = new GridBagConstraints(); //constraints object for object positions
        c.weightx = 1; //weight distribution of all UI objects  //If not set -> less UI movement when typing //try to enable/disable
        c.weighty = 1; //weight distribution of all UI objects
//        c.fill = GridBagConstraints.HORIZONTAL; //stretch horizontal for all UI objects

        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets( 0,10,0,10);

        add(playfieldPanel1, c);

        c.gridx = 1;
        c.gridy = 3;
        add(playfieldPanel2, c);


        setJMenuBar(mainMenuBar);
            mainMenuBar.add(gameMenu);
                gameMenu.add(hostGameMenuItem);
                gameMenu.add(joinGameMenuItem);
                gameMenu.add(quitGameMenuItem);
            mainMenuBar.add(helpMenu);
                helpMenu.add(aboutMenuItem);
                helpMenu.add(changeSettingsMenuItem);

    }

    private void setupLanguage(){
        ResourceBundle.clearCache();
        i18n = ResourceBundle.getBundle("com.ui.i18n");

        UIManager.put("OptionPane.cancelButtonText", i18n.getString("genericCancel") );
        UIManager.put("OptionPane.yesButtonText", i18n.getString("genericYes") );
        UIManager.put("OptionPane.noButtonText", i18n.getString("genericNo") );
        UIManager.put("OptionPane.okButtonText", i18n.getString("genericOk") );
    }

    private void setupTextOfComponents(){
        setTitle(i18n.getString("gameWindowTitle"));
        gameMenu.setText("gameMenuText");
            hostGameMenuItem.setText("hostGameMenuItemText");
            joinGameMenuItem.setText("joinGameMenuItemText");
            quitGameMenuItem.setText("quitGameMenuItemText");
        helpMenu.setText("helpMenuText");
            aboutMenuItem.setText("aboutMenuItemText");
            changeSettingsMenuItem.setText("changeSettingsMenuItemText");
    }


    public static void main(String[] args) {
        GameWindow gameWindow = new GameWindow(new Dimension(2000,1350));
    }
}
