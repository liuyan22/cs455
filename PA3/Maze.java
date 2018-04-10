// Name: Yan Liu
// USC NetID: liu156
// CS 455 PA3
// Spring 2018

import java.util.LinkedList;
import java.util.ArrayList;



/**
   Maze class

   Stores information about a maze and can find a path through the maze
   (if there is one).
   
   Assumptions about structure of the maze, as given in mazeData, startLoc, and endLoc
   (parameters to constructor), and the path:
     -- no outer walls given in mazeData -- search assumes there is a virtual 
        border around the maze (i.e., the maze path can't go outside of the maze
        boundaries)
     -- start location for a path is maze coordinate startLoc
     -- exit location is maze coordinate exitLoc
     -- mazeData input is a 2D array of booleans, where true means there is a wall
        at that location, and false means there isn't (see public FREE / WALL 
        constants below) 
     -- in mazeData the first index indicates the row. e.g., mazeData[row][col]
     -- only travel in 4 compass directions (no diagonal paths)
     -- can't travel through walls

 */

public class Maze {
   
  public static final boolean FREE = false;
  public static final boolean WALL = true;

   
  //Representation Invariants
  private static boolean[][] mazeData; // The maze data is a 2D array that true => wall, false => not wall
  private static MazeCoord startLoc; // The start location of the maze
  private static MazeCoord exitLoc; // The exit location of the maze
  private static LinkedList<MazeCoord> mazePath; // The path of maze is stored in a linkedlist 
   
  

   /**
      Constructs a maze.
      @param mazeData the maze to search.  See general Maze comments above for what
      goes in this array.
      @param startLoc the location in maze to start the search (not necessarily on an edge)
      @param exitLoc the "exit" location of the maze (not necessarily on an edge)
      PRE: 0 <= startLoc.getRow() < mazeData.length and 0 <= startLoc.getCol() < mazeData[0].length
         and 0 <= endLoc.getRow() < mazeData.length and 0 <= endLoc.getCol() < mazeData[0].length

    */
  public Maze(boolean[][] mazeData, MazeCoord startLoc, MazeCoord exitLoc) {
    this.mazeData = mazeData;
    this.startLoc = startLoc;
    this.exitLoc = exitLoc;
    this.mazePath = new LinkedList<MazeCoord>();
  }


   /**
      Returns the number of rows in the maze
      @return number of rows
   */
  public int numRows() {
    return mazeData.length; 
  }

   
   /**
      Returns the number of columns in the maze
      @return number of columns
   */   
  public int numCols() {
    return mazeData[0].length; 
  } 
 
   
   /**
      Returns true iff there is a wall at this location
      @param loc the location in maze coordinates
      @return whether there is a wall here
      PRE: 0 <= loc.getRow() < numRows() and 0 <= loc.getCol() < numCols()
   */
  public boolean hasWallAt(MazeCoord loc) {
    return mazeData[loc.getRow()][loc.getCol()];   
  }
   

   /**
      Returns the entry location of this maze.
    */
  public MazeCoord getEntryLoc() {
    return startLoc;   
  }
   
   
   /**
     Returns the exit location of this maze.
   */
  public MazeCoord getExitLoc() {
    return exitLoc;   
  }

   
   /**
      Returns the path through the maze. First element is start location, and
      last element is exit location.  If there was not path, or if this is called
      before a call to search, returns empty list.

      @return the maze path
    */
  public LinkedList<MazeCoord> getPath() {
    return mazePath; 
  }


   /**
      Find a path from start location to the exit location (see Maze
      constructor parameters, startLoc and exitLoc) if there is one.
      Client can access the path found via getPath method.

      @return whether a path was found.
    */
  public boolean search()  {  
    boolean[][] locationVisited = new boolean [numRows()][numCols()];
    this.mazePath = new LinkedList<MazeCoord>(); // reset the path prior to search
    return PathIsFound(startLoc, locationVisited);
  }

  /** helper function to the search method that finds a valid path
  @param searchLoc current location in maze 
  @param locationVisited location visited while finding path
  */
  private boolean PathIsFound(MazeCoord searchLoc, boolean[][] locationVisited){
    if(hasWallAt(searchLoc)){
      return false;
    }
    if(locationVisited[searchLoc.getRow()][searchLoc.getCol()]){
      return false;
    }
    if(searchLoc.equals(exitLoc)){
      mazePath.add(searchLoc);
      return true;
    }
    
    //mark visited location
    locationVisited[searchLoc.getRow()][searchLoc.getCol()] = true;

    //looking for path from adjacents of start location recursively
    ArrayList<MazeCoord> neighbors = findAdjacents(searchLoc);
    for (MazeCoord neighbor : neighbors) {
      boolean tryFindPath = PathIsFound(neighbor, locationVisited);//try to find a path
      if (tryFindPath) {
        mazePath.addFirst(searchLoc);
        return true;
      }
    }
    return false;     
  }   

  /** helper function: get the adjacents of the current position
  @param current current location in maze
  */
  private ArrayList<MazeCoord> findAdjacents(MazeCoord current) {
    ArrayList<MazeCoord> adjacents = new ArrayList<MazeCoord>();
    // check if current has left adjacent.
    if (current.getCol() - 1 >= 0){
      adjacents.add(new MazeCoord(current.getRow(), current.getCol() - 1));
    }
    // check if current has right adjacent.
    if (current.getCol() + 1 < numCols()) {
      adjacents.add(new MazeCoord(current.getRow(), current.getCol() + 1));
    }
    // check if current has lower adjacent.
    if (current.getRow() - 1 >= 0) {
      adjacents.add(new MazeCoord(current.getRow() - 1, current.getCol()));
    }
    // check if current has upper adjacent.
    if (current.getRow() + 1 < numRows()) {
      adjacents.add(new MazeCoord(current.getRow() + 1, current.getCol()));
    }
    return adjacents;
  }

}
   