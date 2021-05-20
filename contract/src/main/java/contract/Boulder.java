package contract;
 

public class Boulder implements Cell{
    private boolean falling;
    public Boulder() {
        falling = false;
    }
    public boolean getFalling() {
        return falling;
    }
    public void setFalling(boolean b) {
        falling = b;
    }
}
