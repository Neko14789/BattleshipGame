package com.ui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Locale;


public class BasicUi extends JFrame { //Frame

    //Labels


    //TextFields


    //Buttons
    private JButton buttonNewFleet;

    //menu
    private JMenuBar mainMenuBar;
    private JMenu fileMenu;
    private JMenuItem exitMenuItem;
    private JMenu helpMenu;
    private JMenuItem aboutMenuItem;

    private JButton button;
    private ArrayList<JButton> buttonArrayList;


    //vars

    private static final String fileNameTankLevels = "TankLevels.data";
    private static final String fileNameUserSettings = "UserSettings.data";
    private static final Locale defaultLocale = Locale.getDefault();


    public BasicUi(){

        setLayout(new GridBagLayout()); //set UI Layout to grid bag layout

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(520,250);
        setResizable(true);
        initComponents(); //set definition and initial settings of the components
        updateTextOfComponents();

        addListeners(); //add listeners to objects

        initUi(); //set the location and nesting of UI objects

        setLocationRelativeTo(null); //app location in the middle of the screen
        setVisible(true);

//        System.out.println("App Ready");
    }




    private void saveObject(String fileName, Object object){ //Method to save any type of Object
        ObjectOutputStream out = null; //new ObjectOutputStream
        try {
            out = new ObjectOutputStream(new FileOutputStream(fileName));
        } catch (Exception ioException) {
            ioException.printStackTrace(); //prints our the exception - only in dev mode
        }
        try {
            assert out != null;
            out.writeObject(object);
        } catch (IOException ioException) {
            ioException.printStackTrace(); //prints our the exception - only in dev mode
        }

        try {
            out.close();
        } catch (IOException ioException) {
            ioException.printStackTrace(); //prints our the exception - only in dev mode
        }
    }

    private Object loadObject(String fileName, Object object) throws IOException { //Method to load any type of Object
        File tmpDir = new File(fileName); //creates temp File
        boolean exists = tmpDir.exists(); //checks via temp File if the file to load exists
        if(exists) {
            ObjectInputStream in = null; //new ObjectInputStream
            try {
                in = new ObjectInputStream(new FileInputStream(fileName));
            } catch (FileNotFoundException fileNotFoundException ) {
                fileNotFoundException.printStackTrace(); //prints our the exception - only in dev mode
            }
            try {
                assert in != null;
                object = in.readObject();
            } catch (ClassNotFoundException | FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace(); //prints our the exception - only in dev mode
            }
            try {
                    in.close();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace(); //prints our the exception - only in dev mode
            }

        } else{
            throw new FileNotFoundException("No File"); //throwing No File exception
        }
        return(object);
    }


    private void addListeners(){
        buttonNewFleet.addActionListener(e -> //starts Consumption calculation by calling the respective method
                createFleet());

        aboutMenuItem.addActionListener(e -> //
                JOptionPane.showMessageDialog(BasicUi.this, "Battleship Fleet by Nico Hübsch \\n\\n© Nico Hübsch", "About",  JOptionPane.PLAIN_MESSAGE)); //show About Dialog

        exitMenuItem.addActionListener(e ->
                System.exit(0)); //code "0" for program termination when everything went fine (non 0 = abnormal behaviour)
    }

    private void createFleet() {

    }

    private void initComponents(){

        //labels


        //text fields


        //buttons
        buttonNewFleet = new JButton();
        getRootPane().setDefaultButton(buttonNewFleet); //makes the calculate button the default button of the window

        //menu and respective Accelerators and Mnemonics, indentation is representative for final Structure
        mainMenuBar = new JMenuBar();
            fileMenu = new JMenu();
            fileMenu.setMnemonic(KeyEvent.VK_F);
                exitMenuItem = new JMenuItem();
                exitMenuItem.setMnemonic(KeyEvent.VK_X);
                exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.ALT_DOWN_MASK));

            helpMenu = new JMenu();
            helpMenu.setMnemonic(KeyEvent.VK_H);
                aboutMenuItem = new JMenuItem();
                aboutMenuItem.setMnemonic(KeyEvent.VK_A);
                aboutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.ALT_DOWN_MASK));

        //vars

        button = new JButton();
        int x = 1;
        buttonArrayList.add(button);
    }

    private void updateTextOfComponents(){
        //Application
        setTitle("Battleship Fleet");

        //Labels


        //buttons
        buttonNewFleet.setText("New Fleet");

        //menus
        fileMenu.setText("File");
            exitMenuItem.setText("Exit");
        helpMenu.setText("Help");
            aboutMenuItem.setText("About");
    }

    private void initUi(){
        //Menu bar, indentation is representative for final Structure
        setJMenuBar(mainMenuBar);
            mainMenuBar.add(fileMenu);
                fileMenu.add(exitMenuItem);
            mainMenuBar.add(helpMenu);
                helpMenu.add(aboutMenuItem);

        //for all components
        GridBagConstraints c = new GridBagConstraints(); //constraints object for object positions
//        c.weightx = 1; //weight distribution of all UI objects  //If not set -> less UI movement when typing //try to enable/disable
        c.weighty = 1; //weight distribution of all UI objects
        c.fill = GridBagConstraints.HORIZONTAL; //stretch horizontal for all UI objects

        //for left label components
        c.gridx = 0;
        c.insets = new Insets( 0,10,0,10);



        //for right text field components
        c.weightx = 2;
        c.gridx = 1;
        c.insets = new Insets( 0,0,0,10);


        //for bottom Centered components
        c.insets = new Insets( 0,10,0,10);
        c.gridx = 0;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.CENTER;

        c.gridy = 6;
        add(buttonNewFleet, c);

        c.gridy = 7;
    }
}
