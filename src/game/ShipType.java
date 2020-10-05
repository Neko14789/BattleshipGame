package game;

public enum ShipType {
    Carrier (5, "C"),
    Battleship (4, "B"),
    Destroyer (3, "D"),
    Submarine (2, "S");

    int length;
    String typeShort;

    ShipType(int length, String letter){
        this.length = length;
        this.typeShort = letter;
    }

    public int getLength(){
        return length;
    }

    public String getTypeShort(){
        return typeShort;
    }
}
