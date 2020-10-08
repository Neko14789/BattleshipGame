package ui;

import game.Coordinate;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PlayfieldPanel extends JPanel {
    private final Dimension playfieldSize;
    private final ArrayList<CustomButton> buttons = new ArrayList<>();

    PlayfieldPanel(Dimension playfieldSize, String title){
        this.playfieldSize = playfieldSize;
        setLayout(new GridLayout(playfieldSize.height, playfieldSize.width));

        TitledBorder titledBorder = BorderFactory.createTitledBorder(title);
//        titledBorder.setTitleColor(Color.BLACK);
//        setBorder(new LineBorder(Color.BLACK, 2));
        setBorder(titledBorder);


        setupButtons();
    }

    private void setupButtons(){
        for (int x = 0; x < playfieldSize.height; ++x) {
            for (int y = 0; y < playfieldSize.width; ++y) {
                CustomButton b = new CustomButton(x + ":" + y);
                b.setCoordinate(new Coordinate(x,y));

                b.setPreferredSize(new Dimension(60,60));

                b.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.out.println(b.getCoordinate().x + ":" + b.getCoordinate().y + "-->" + b.getCoordinate().shot);
                        b.getCoordinate().shot = true;
                        b.setText("X");
                        b.setEnabled(false);
                    }
                });
                add(b);
                buttons.add(b);
            }
        }
    }
}
