package com.game;

import java.util.ArrayList;

public class Ship {
    private ShipAlignment shipAlignment;
    private Coordinate shipStartCoordinate;
    private ShipType shipType;
    private ArrayList<Coordinate> shipCoordinates;
    private ArrayList<Coordinate> shipBlockingCoordinates;

    public Ship(ShipType shipType, ShipAlignment shipAlignment, Coordinate shipStartCoordinate){
        this.shipAlignment = shipAlignment;
        this.shipStartCoordinate = shipStartCoordinate;
        this.shipType = shipType;

        calculateShipCoordinates();
        calculateShipBlockingCoordinates();
    }

    public void calculateShipCoordinates() {
        int x = 0;
        int y = 0;
        shipCoordinates = new ArrayList<>();
        for (int i = 0; i < shipType.getLength(); i++) {
            if(shipAlignment == ShipAlignment.HORIZONTAL) {
                x = shipStartCoordinate.x + i;
                y = shipStartCoordinate.y;
            } else if (shipAlignment == ShipAlignment.VERTICAL) {
                x = shipStartCoordinate.x;
                y = shipStartCoordinate.y + i;
            }
            Coordinate coordinate = new Coordinate(x,y);
            shipCoordinates.add(coordinate);

        }
        //Debug
//        System.out.println("ShipCoordinates");
//        shipCoordinates.forEach(coordinate -> System.out.println("(" + coordinate.x + ":" + coordinate.y + ")"));
    }

    public void calculateShipBlockingCoordinates() {
        int x;
        int y;
        shipBlockingCoordinates = new ArrayList<>();
        for (int i = -1; i < shipType.getLength()+1; i++) {

            switch(shipAlignment) {
                case HORIZONTAL:
                    for (int o = -1; o <= 1; o++) {
                        x = shipStartCoordinate.x + i;
                        y = shipStartCoordinate.y + o;
                        Coordinate coordinate = new Coordinate(x, y);
                        shipBlockingCoordinates.add(coordinate);

                    }
                    break;
                case VERTICAL:
                    for (int o = -1; o <= 1; o++) {
                        x = shipStartCoordinate.x + o;
                        y = shipStartCoordinate.y + i;
                        Coordinate coordinate = new Coordinate(x, y);
                        shipBlockingCoordinates.add(coordinate);

                    }
                    break;
            }
        }
        //Debug
//        System.out.println("ShipBlockingCoordinates");
//        shipBlockingCoordinates.forEach(coordinate -> System.out.println("(" + coordinate.x + ":" + coordinate.y + ")"));
    }

    public boolean isAlive() {
        return shipCoordinates.stream().anyMatch(coordinate -> !coordinate.shot);
    }

    public boolean wasHit(Coordinate checkCoordinate) {
        return shipCoordinates.stream().anyMatch(coordinate -> coordinate.equalCoordinates(checkCoordinate));
    }

    public boolean shoot(Coordinate checkCoordinate) {
        boolean hit = shipCoordinates.stream().anyMatch(coordinate -> coordinate.equalCoordinates(checkCoordinate));
        if(hit){
            for(Coordinate cord : shipCoordinates) {
                if(cord.equalCoordinates(checkCoordinate)){
                    cord.shot=true;
                }
            }
        }
        return hit;
    }

    public ArrayList<Coordinate> getShipCoordinates(){
        return shipCoordinates;
    }

    public ArrayList<Coordinate> getShipBlockingCoordinates(){
        return shipBlockingCoordinates;
    }

    public ShipType getShipType() {
        return shipType;
    }

    public static void main(String[] args) {
        Ship ship = new Ship(ShipType.Submarine,ShipAlignment.HORIZONTAL, new Coordinate(0,0));
        System.out.println("isAlive: " + ship.isAlive());
        System.out.println("wasHit: " + ship.wasHit(new Coordinate(1,0)));
    }
}
