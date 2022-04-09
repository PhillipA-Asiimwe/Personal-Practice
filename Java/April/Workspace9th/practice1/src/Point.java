public class Point {
    private int xLoc , yLoc;

    public Point(){
        xLoc=0;
        yLoc=0;
    }

    public Point(int x, int y){ 
        xLoc = x; 
        yLoc = y;
    }

    public String toString(){
        return "Point("+ xLoc +" , "+ yLoc+").";
    }

    public int getxLoc() { return xLoc;}
    public int getyLoc() { return yLoc;}
    public void setX(int x) {xLoc = x;}
    public void setY(int y) {yLoc = y;}
    public Point getPoint(){return this;}
    
}
