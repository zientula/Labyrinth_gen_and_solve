package generators;

import java.util.List;
/**
 * the Cell class represents a single cell built from tile types
 */


public class Cell {
    private boolean right,down;

    private List<Cell> set;

    private int x,y;    //coordinates

    Cell(int a, int b){

        x=a;

        y=b;

        right=false;

        down=true;

        set=null;

    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public List<Cell> getSet() {
        return set;
    }

    public void setSet(List<Cell> set) {
        this.set = set;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
