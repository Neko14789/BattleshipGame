package ui;

import game.Coordinate;
import game.Fleet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ButtonPanel extends JPanel {
    private Fleet fleet;
    private final ArrayList<CustomButton> buttons = new ArrayList<>();

    public ButtonPanel(Fleet fleet, Dimension dimension) {
        this.fleet = fleet;

        int width = dimension.width;
        int height = dimension.height;
        setPreferredSize(new Dimension(width*65, height*65));

        setLayout(new GridLayout(height, width));
        for (int x = 0; x < height; ++x) {
            for (int y = 0; y < width; ++y) {
                CustomButton b = new CustomButton(x + ":" + y);
                b.setCoordinate(new Coordinate(x,y));



                b.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.out.println(b.getCoordinate().x + ":" + b.getCoordinate().y + "-->" + b.getCoordinate().shot);
                        b.getCoordinate().shot = true;
                        b.setText("X");
//                        b.setEnabled(false);
                        b.setBackground(Color.cyan);
                    }
                });




                add(b);
                buttons.add(b);

            }
        }
        paintButtons();
    }

    public void importFleet(Fleet fleet){
        this.fleet = fleet;
        paintButtons();
    }

    public void paintButtons() {
        for(CustomButton b : buttons){
            if ( fleet.fleet.stream().anyMatch(ship ->
            ship.getShipCoordinates().stream().anyMatch(coordinate ->
                coordinate.equalCoordinates(b.getCoordinate())))) {
                    b.setBackground(Color.BLACK);
                } else {
                if ((b.getCoordinate().x + b.getCoordinate().y) % 2 == 0) { //Der Modulo Operator ermittelt den Restwert einer Division. Beispielsweise würde 4 Modulo 2 den Restwert 0 ergeben. 5 Modulo 2 würde den Restwert 1 ergeben. Denn 5 geteilt durch 2 ist gleich 2 Rest 1.
                    b.setBackground(new Color(255, 255, 255));
                } else {
                    b.setBackground(new Color(240, 240, 240));
                }
            }
        }

    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(new Dimension(700, 750));

                frame.setLayout(new FlowLayout());
                frame.setLocationRelativeTo(null);


                long seed;
                seed = System.currentTimeMillis();
                Dimension boardSize = new Dimension(10,10);

                Fleet fleet = new Fleet(boardSize.width ,boardSize.height, seed);
                ButtonPanel buttonPanel = new ButtonPanel(fleet, boardSize );
                frame.add(buttonPanel);

                JButton button = new JButton("New Board");

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("DEBUG: Creating new board");
                        button.setEnabled(false);
                        buttonPanel.importFleet(new Fleet(boardSize.width,boardSize.height, System.currentTimeMillis()));
                        button.setEnabled(true);
                    }
                });

                frame.add(button);

                frame.setVisible(true);
            }
        });
    }
}
