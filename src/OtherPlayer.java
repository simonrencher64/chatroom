

public class OtherPlayer {
    public int id;
    public int x;
    public int y;

    public OtherPlayer(int id,int x,int y){
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public void updateLocation(int x, int y){
        this.x = x;
        this.y = y;
    }
}
