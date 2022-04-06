//Author Phillip Amanya
import javafx.geometry.Point2D;
import java.util.*;
public class Maze {
    private static final byte OPEN = 0; 
    private static final byte WALL = 1;
    private static final byte VISITED = 2;

    private int         rows,columns;
    private byte[][]    grid;

    //Constructor to make a maze of the given size rw and cl
    public Maze(int rw, int cl){
        rows = rw; 
        columns = cl;
        grid = new byte[rw][cl];
        for (rw=0;rw<rows;rw++){
            for (cl=0; cl<columns;cl++){
                grid[rw][cl] = WALL;
            }
        }
    }

    public int getRows(){return rows;}
    public int getColumns(){return columns;}

    //returns true if wall at location 
    public boolean wallAt(int rw, int cl){
        return grid[rw][cl] == WALL;
    }

    //returns true if location has been visited
    public boolean visitedAt(int rw, int cl){
        return grid[rw][cl]==VISITED;
    }

    //puts Visited at gven location 
    public void placeVisitAt(int rw, int cl){
        grid[rw][cl]=VISITED;
    }

    //removes Visited at given location
    public void removeVisitAT(int rw, int cl){
        grid[rw][cl] = OPEN;
    }

    //puts a wall at given location 
    public void placeWallAt(int rw, int cl){
        grid[rw][cl]=WALL;
    }

    //remove a wall from the given location 
    public void removeWallAt(int rw, int cl){
        grid[rw][cl]= 0; 
    }

    //Carve out a maze
    public void carve(){
        int startRow = (int)(Math.random()*(rows-2))+1;
        int startCol = (int)(Math.random()*(columns-2))+1;
        carve(startRow,startCol);
    }
    
    public void carve(int rw, int cl){
        ArrayList<Integer> rowOffSets = new ArrayList<Integer>(Arrays.asList(-1,1,0,0));
        ArrayList<Integer> colOffSets = new ArrayList<Integer>(Arrays.asList(0,0,-1,1));
        if (rw==0 || rw == rows-1 || cl ==0 || cl==columns){
            return;
        }else if (wallAt(rw, cl)){
            int count = 0 ; 
            if (wallAt(rw+1, cl)){count++;}
            if (wallAt(rw-1, cl)){count++;}
            if (wallAt(rw, cl+1)){count++;}
            if (wallAt(rw, cl-1)){count++;}
            if(count>=3){
                removeWallAt(rw, cl);
                int random = new Random().nextInt(4);
                switch(random){
                    case 0 :
                    carve(rw + rowOffSets.get(0),cl + colOffSets.get(0));
                    carve(rw + rowOffSets.get(1),cl + colOffSets.get(1));
                    carve(rw + rowOffSets.get(2),cl + colOffSets.get(2));
                    carve(rw + rowOffSets.get(3),cl + colOffSets.get(3));
                    break;

                    case 1:
                    carve(rw + rowOffSets.get(1),cl + colOffSets.get(1));
                    carve(rw + rowOffSets.get(2),cl + colOffSets.get(2));
                    carve(rw + rowOffSets.get(3),cl + colOffSets.get(3));
                    carve(rw + rowOffSets.get(0),cl + colOffSets.get(0));
                    break;

                    case 2:
                    carve(rw + rowOffSets.get(2),cl + colOffSets.get(2));
                    carve(rw + rowOffSets.get(3),cl + colOffSets.get(3));
                    carve(rw + rowOffSets.get(0),cl + colOffSets.get(0));
                    carve(rw + rowOffSets.get(1),cl + colOffSets.get(1));
                    break;

                    case 3:
                    carve(rw + rowOffSets.get(3),cl + colOffSets.get(3));
                    carve(rw + rowOffSets.get(0),cl + colOffSets.get(0));
                    carve(rw + rowOffSets.get(1),cl + colOffSets.get(1));
                    carve(rw + rowOffSets.get(2),cl + colOffSets.get(2));
                    break; 
                    default:
                    System.out.println("error");
                    return;
                }
            }

        }else {return;}

    }
    //determain the lognest path in the maze from start
    public ArrayList<Point2D> longestPath(){
        int highest =0; 
        ArrayList<Point2D> answer = new ArrayList<>();
        for (int i=0; i<2 ; i++){
            for (int r=0; r<rows;r++){
                for (int c =0; c<columns;c++){
                    if(i==0){
                        int temp = longestPathFrom(r,c).size();
                        if (temp>highest){
                            highest = temp;
                        }
                    }else {
                        if(highest == longestPathFrom(r,c).size()){
                            answer = longestPathFrom(r,c);
                        }
                    }
                }
            }
        }
        return answer;
    }

    public ArrayList<Point2D> longestPathFrom(int r, int c ){
        ArrayList<Point2D> path = new ArrayList<Point2D>();
        if (wallAt(r, c)|| visitedAt(r,c)){
            return path; 
        }else{
            placeVisitAt(r,c);
            ArrayList<Point2D> temp1=longestPathFrom(r-1,c);
            ArrayList<Point2D> temp2=longestPathFrom(r+1,c);
            ArrayList<Point2D> temp3=longestPathFrom(r,c-1);
            ArrayList<Point2D> temp4=longestPathFrom(r,c+1);

            int MAxlength=Math.max(temp1.size(),Math.max(temp2.size(),Math.max(temp3.size(),temp4.size())));

            if (MAxlength==temp1.size()){temp1.add(new Point2D(r,c));removeVisitAT(r,c);return temp1;}

            if (MAxlength==temp2.size()){temp2.add(new Point2D(r,c));removeVisitAT(r,c);return temp2;}

            if (MAxlength==temp3.size()){temp3.add(new Point2D(r,c));removeVisitAT(r,c);return temp3;}

            if (MAxlength==temp4.size()){temp4.add(new Point2D(r,c));removeVisitAT(r,c);return temp4;}
        }
        return path;

        
    }

}
