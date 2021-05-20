package contract;
 

public class Diamond implements Cell {
    private boolean falling;
    public Diamond() {
        falling = false;
    }
    public boolean getFalling() {
        return falling;
    }
    public void setFalling(boolean b) {
        falling = b;
    }
}