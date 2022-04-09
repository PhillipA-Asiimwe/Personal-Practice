public class Line {
    //composition is found when we create point objects in this line class to utilize
    private Point beginPoint, endpPoint;
    private int xDiff , yDiff ;

    private Line(Point begin, Point end){
        this.beginPoint = begin; 
        this.endpPoint = end;
        xDiff = Math.abs((beginPoint.getxLoc()-endpPoint.getxLoc()));
        yDiff = Math.abs((beginPoint.getyLoc()-endpPoint.getyLoc()));
    }

    private Line(int beginX, int beginY, int endX, int endY){
        beginPoint = new Point(beginX, endX);
        endpPoint = new Point(endX,endY);
        xDiff = Math.abs((beginPoint.getxLoc()-endpPoint.getxLoc()));
        yDiff = Math.abs((beginPoint.getyLoc()-endpPoint.getyLoc()));
    }
    
    public String toString(){
        return "Line starts at" + beginPoint.toString() + " and ends at " + endpPoint.toString();
    }

    public Point getBePoint(){return beginPoint;}
    public Point getEdPoint(){return endpPoint;}
    public int getBeginX() {return beginPoint.getxLoc(); }
    public int getBeginY() {return beginPoint.getyLoc(); }
    public int getEndX() {return endpPoint.getxLoc(); }
    public int getEndY() {return endpPoint.getyLoc(); }
   
    public void setBeginX(int x) { beginPoint.setX(x); }
    public void setBeginY(int y) { beginPoint.setY(y); }
    public void setBeginXY(int x, int y) { beginPoint.setX(x); beginPoint.setY(y);}
    public void setEndX(int x ) { endpPoint.setX(x); }
    public void setEndY(int y ) { endpPoint.setY(y); }
    public void setEndXY(int x, int y) { endpPoint.setX(x); endpPoint.setY(y); }

    public int getLength(){ 
        return (int)Math.sqrt((xDiff*xDiff)+(yDiff*yDiff));
    }
    public double getGradient(){
        return Math.atan2(yDiff, xDiff);

    }
}
