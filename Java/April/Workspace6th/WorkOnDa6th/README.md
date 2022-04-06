Maze Generation 
Maze.java contains the maze class represented by size rows X column. Each location in the maze is a wall or an open space, black and white respectively. 

carve():Initially, all created mazes are filled with walls, so that there are no open spaces. When called, the carve method calls
carve(int r, int c) which is supposed to "carve" out the maze by removing walls in a random fashion
by starting at position (r, c) in the maze.

The idea behind the algorithm is as follows:
CARVE(r, c) {
if location (r, c) is along the border of the maze, then
    don't remove the wall here, size we want a solid border around the maze at all times.
otherwise,
     if location (r, c) is an open space,
    we already removed the wall here, so there is nothing more to do.
otherwise
    we should remove the wall at this location if there are at least 3 walls around it ... then
    we should recursively carve out the maze in all 4 locations around (r,c).
}

You will test the  code by using the provided MazeCreator application which makes use of the
DimensionsDialog class as well. Just run the code and you should see the interface.