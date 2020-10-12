package com.ui;

import com.game.Coordinate;
import com.game.FleetGenerator;
import com.game.Ship;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PlayfieldPanel extends JPanel {
    private Dimension playfieldSize;
    private final ArrayList<CustomButton> buttons = new ArrayList<>();
    private final String title;
    private final TitledBorder titledBorder;
    private ArrayList<Ship> fleet;

    PlayfieldPanel(String title){
        this.title = title;
        titledBorder = BorderFactory.createTitledBorder(title);
//        titledBorder.setTitleColor(Color.BLACK);
//        setBorder(new LineBorder(Color.BLACK, 2));
        setBorder(titledBorder);

    }

    PlayfieldPanel(Dimension playfieldSize, String title){
        this.title = title;
        titledBorder = BorderFactory.createTitledBorder(title);
//        titledBorder.setTitleColor(Color.BLACK);
//        setBorder(new LineBorder(Color.BLACK, 2));
        setBorder(titledBorder);

        setPlayfieldSize(playfieldSize);
    }
    public void setPlayfieldSize(Dimension playfieldSize){
        buttons.forEach(this::remove);
        this.playfieldSize = playfieldSize;
        setupButtons();

        setLayout(new GridLayout(playfieldSize.height, playfieldSize.width));
    }

    public void setFleet(ArrayList<Ship> fleet){
        this.fleet = fleet;
    }

    public ArrayList<Ship> getFleet(){
        return this.fleet;
    }

    private void setupButtons(){
        for (int x = 0; x < playfieldSize.height; ++x) {
            for (int y = 0; y < playfieldSize.width; ++y) {
                CustomButton b = new CustomButton(x + ":" + y);
                b.setCoordinate(new Coordinate(x,y));

                b.setPreferredSize(new Dimension(52,52));

                b.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.out.println(title + ": "+ b.getCoordinate().x + ":" + b.getCoordinate().y + "-->" + b.getCoordinate().shot);
                        b.getCoordinate().shot = true;
                        b.setText("X");
                        b.setBackground(Color.black);
                        b.setEnabled(false);
                    }
                });

                if ((b.getCoordinate().x + b.getCoordinate().y) % 2 == 0) { //Der Modulo Operator ermittelt den Restwert einer Division. Beispielsweise würde 4 Modulo 2 den Restwert 0 ergeben. 5 Modulo 2 würde den Restwert 1 ergeben. Denn 5 geteilt durch 2 ist gleich 2 Rest 1.
                    b.setBackground(new Color(255, 255, 255));
                } else {
                    b.setBackground(new Color(240, 240, 240));
                }

                add(b);
                buttons.add(b);
            }
        }
    }
}
