package com.game;

import java.util.ArrayList;
import java.util.Random;

public class FleetGenerator {
    private final Random random;
    public final ArrayList<Ship> fleet;

    public FleetGenerator(){
        random = new Random();
        fleet = new ArrayList<>();
    }

    public ArrayList<Ship> createFleet(int boardWidth, int boardHeight, long fleetSeed){


        random.setSeed(fleetSeed);

        while (!(fleet.size() == 10)) {

            ShipType shipType = ShipType.Carrier;
            fleet.clear();
            int impossibleCounter = 0;
            for (int counter = 0; counter < 10; counter++) {
                switch (counter){
                    case 0:
                        shipType = ShipType.Carrier;
                        break;
                    case 1:
                        shipType = ShipType.Battleship;
                        break;
                    case 3:
                        shipType = ShipType.Destroyer;
                        break;
                    case 6:
                        shipType = ShipType.Submarine;
                        break;
                    }

                boolean shipAdded = false;
                while (!shipAdded) {
                    Ship ship = createShip(boardHeight, boardWidth, shipType);

                    if ( fleet.stream().allMatch(ship2 -> checkPlacementValid(ship, ship2)) ) {
                        fleet.add(ship);
                        shipAdded=true;
                    } else if (fleet.isEmpty()) {
                        fleet.add(ship);
                        shipAdded=true;
                    } else {
                        shipAdded=false;
                    }

                    impossibleCounter++;
//                    System.out.println("DEBUG: Ship creation try's: " + impossibleCounter);
                    if(impossibleCounter >= 100) {
                        System.out.println("DEBUG: Reset Fleet");
                        break;
                    }
                }
                if(impossibleCounter >= 100) {
                    break;
                }
            }
        }
        System.out.println("DEBUG: Fleet created");
        return fleet;
    }

    private boolean checkPlacementValid(Ship ship1, Ship ship2) {
        return ship1.getShipCoordinates().stream().noneMatch(coordinate1 ->
                ship2.getShipBlockingCoordinates().stream().anyMatch(coordinate1::equalCoordinates));
    }

    private Ship createShip(int boardSizeX, int boardSizeY, ShipType shipType){
        ShipAlignment shipAlignment = ShipAlignment.HORIZONTAL;
            int x = 0;
            int y = 0;
            switch (random.nextInt(2)){
                case 0:
                    shipAlignment = ShipAlignment.HORIZONTAL;
                    x = random.nextInt(boardSizeX -shipType.getLength());
                    y = random.nextInt(boardSizeY);
                    break;
                case 1:
                    shipAlignment = ShipAlignment.VERTICAL;
                    x = random.nextInt(boardSizeX);
                    y = random.nextInt(boardSizeY -shipType.getLength());
                    break;
            }
            return new Ship(shipType, shipAlignment, new Coordinate(x,y));

    }

    public static void main(String[] args) {
        FleetGenerator fleetGenerator = new FleetGenerator();
        ArrayList<Ship> fleet = fleetGenerator.createFleet(10,10, 3);
    }
}
