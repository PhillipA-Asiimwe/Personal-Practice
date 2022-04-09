public class LineInherit extends Point {
    private Point endPoint;// We will inherite the begining from the Point class in the constructer 
    private int xDiff , yDiff ;
    public LineInherit(int beginX, int beginY, int endX, int endY){
        super(beginX, beginY);
        this.endPoint = new Point(endX,endY);
        xDiff = Math.abs((super.getxLoc()-super.getxLoc()));
        yDiff = Math.abs((super.getyLoc()-super.getyLoc()));

    }
    public LineInherit(Point begin, Point end){
        super(begin.getxLoc(),begin.getyLoc());
        this.endPoint =end; 
        xDiff = Math.abs((super.getxLoc()-super.getxLoc()));
        yDiff = Math.abs((super.getyLoc()-super.getyLoc()));

    }

    public String toString(){
        return "Line starts at" + super.toString() + " and ends at " + endPoint.toString();
    }

    public Point getBePoint(){return super.getPoint();}
    public Point getEdPoint(){return endPoint;}
    public int getBeginX() {return super.getxLoc(); }
    public int getBeginY() {return super.getyLoc(); }
    public int getEndX() {return endPoint.getxLoc(); }
    public int getEndY() {return endPoint.getyLoc(); }
   
    public void setBeginX(int x) { super.setX(x); }
    public void setBeginY(int y) { super.setY(y); }
    public void setBeginXY(int x, int y) { super.setX(x); super.setY(y);}
    public void setEndX(int x ) { endPoint.setX(x); }
    public void setEndY(int y ) { endPoint.setY(y); }
    public void setEndXY(int x, int y) { endPoint.setX(x); endPoint.setY(y); }

    public int getLength(){ 
        return (int)Math.sqrt((xDiff*xDiff)+(yDiff*yDiff));
    }
    public double getGradient(){
        return Math.atan2(yDiff, xDiff);

    }


    
}
