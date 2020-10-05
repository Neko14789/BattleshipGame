package game;

public class Coordinate {
    public int x;
    public int y;
    public boolean shot = false;

    public Coordinate(){
        this.x = 0;
        this.y = 0;
    }

    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }

    public boolean equalCoordinates(Coordinate other) {
        return other.x == this.x && other.y == this.y;
    }
}
