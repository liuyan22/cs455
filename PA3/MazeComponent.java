// Name: Yan Liu
// USC NetID: liu156  
// CS 455 PA3
// Spring 2018

import java.awt.Graphics;
import javax.swing.JComponent;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;
import java.util.LinkedList;
import java.util.ListIterator;
import java.awt.geom.Line2D;

/**
   MazeComponent class
   
   A component that displays the maze and path through it if one has been found.
*/
public class MazeComponent extends JComponent
{

  //Representation Invariants
  private static final int START_X = 10; // top left of corner of maze in frame
  private static final int START_Y = 10;
  private static final int BOX_WIDTH = 20;  // width and height of one maze "location"
  private static final int BOX_HEIGHT = 20;
  private static final int INSET = 2;  
                    // how much smaller on each side to make entry/exit inner box
  private static final int multiplier = 2; 
                    //how many times each BOX_WIDTH, BOX_HEIGHT OR INSERT need to be substracted/added/multiplied/divided            
  private Maze maze;
  private static final Color colorOfWalls = Color.BLACK; //set the color of wall => black
  private static final Color colorOfEntryLoc = Color.YELLOW;
  private static final Color colorOfExitLoc = Color.GREEN;
  private static final Color colorOfBoarder = Color.BLACK;
  private static final Color colorOfPath = Color.BLUE;
  
   /**
      Constructs the component.
      @param maze   the maze to display
   */
  public MazeComponent(Maze maze) 
  {   
    this.maze = maze;
  }

   
   /**
     Draws the current state of maze including the path through it if one has
     been found.
     @param g the graphics context
   */
  public void paintComponent(Graphics g)
  {
    Graphics2D g2 = (Graphics2D) g;
      
    //construct and draw maze boarder
    Rectangle mazeFrame = new Rectangle(START_X, START_Y, BOX_WIDTH * maze.numCols(), BOX_HEIGHT * maze.numRows());
    g2.draw(mazeFrame);
    g2.setColor(colorOfBoarder);

    //paint start location, exit location, wall, and draw path if a valid path is found
    LinkedList<MazeCoord> pathLoc = maze.getPath();
    drawMaze(g2); 
    if (pathLoc.size() != 0) {
      drawPath(g2, pathLoc);
    }
  }

  /**
  the private method that paints start location, exit location, and wall
  @param g2 the graphics context
  */
  private void drawMaze(Graphics2D g2) {
    boolean isWall = false;
    //paint start location & exit location
    g2.setColor(colorOfEntryLoc);
    g2.fill(getRecFromLoc(maze.getEntryLoc(), isWall));
    g2.setColor(colorOfExitLoc);
    g2.fill(getRecFromLoc(maze.getExitLoc(), isWall));

    //paint every one of "wall location"     
    isWall = true;   
    g2.setColor(colorOfWalls);
    int numOfRows = maze.numRows();
    int numOfCols = maze.numCols(); 
    for(int i = 0; i < numOfRows; i++){
      for(int j = 0; j < numOfCols; j++){
        MazeCoord mazeLoc = new MazeCoord(i, j);
        if(maze.hasWallAt(mazeLoc)){
          g2.fill(getRecFromLoc(mazeLoc, isWall));
        } 
      } 
    }
  }

  /**
  the private method that draws the path
  @param g2 the graphics context
  @param pathLoc the path locations 
   */
  private void drawPath(Graphics2D g2, LinkedList<MazeCoord> pathLoc) {
    ListIterator<MazeCoord> itr = pathLoc.listIterator();
    MazeCoord entryLoc = itr.next(); //get the start location
    int startLine_x = START_X + BOX_WIDTH * entryLoc.getCol() + BOX_WIDTH / multiplier; //path starting position in x direction 
    int startLine_y = START_Y + BOX_HEIGHT * entryLoc.getRow() + BOX_HEIGHT / multiplier;

    while(itr.hasNext()){
      MazeCoord loc = itr.next(); //get the next position
      int endLine_x = START_X + BOX_WIDTH * loc.getCol() + BOX_WIDTH / multiplier; //path ending position in x direction 
      int endLine_y = START_Y + BOX_HEIGHT * loc.getRow() + BOX_HEIGHT / multiplier;
      Line2D.Double path = new Line2D.Double(startLine_x, startLine_y, endLine_x, endLine_y); // set up corresponding path
      g2.setColor(colorOfPath);
      g2.draw(path);
      startLine_x = endLine_x;//reset start point
      startLine_y = endLine_y;
    }      
  }

  /**
  private method that constructs rectangle for start location, exit location, and wall
  @param mazeLoc location in maze 
  @param isWall isWall => true if location is a wall
  */
  private Rectangle getRecFromLoc(MazeCoord mazeLoc, boolean isWall){
    int xCoord, yCoord, width, height;
    //construct rectangle if location is a wall
    if (isWall) {
      xCoord = mazeLoc.getCol() * BOX_WIDTH + START_X;//x coordinate of rectangle
      yCoord = mazeLoc.getRow() * BOX_HEIGHT + START_Y;
      width = BOX_WIDTH;
      height = BOX_HEIGHT;
    } else {
      xCoord = mazeLoc.getCol() * BOX_WIDTH + START_X + INSET;
      yCoord = mazeLoc.getRow() * BOX_HEIGHT + START_Y + INSET;
      width = BOX_HEIGHT - INSET * multiplier;
      height = BOX_HEIGHT - INSET * multiplier;
    }
    return new Rectangle(xCoord, yCoord, width, height);
  }
}



