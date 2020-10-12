package com.ui;

import com.game.Coordinate;

import javax.swing.*;

public class CustomButton extends JButton {
    private Coordinate coordinate;
    public CustomButton(String text){
       super(text);
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }
}
