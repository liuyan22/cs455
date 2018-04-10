// Name: Yan Liu
// USC NetID: liu156
// CS 455 PA3
// Spring 2018


import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JFrame;
import java.io.BufferedReader;
import java.io.*;
import java.util.Arrays;


/**
 * MazeViewer class
 * 
 * Program to read in and display a maze and a path through the maze. At user
 * command displays a path through the maze if there is one.
 * 
 * How to call it from the command line:
 * 
 *      java MazeViewer mazeFile
 * 
 * where mazeFile is a text file of the maze. The format is the number of rows
 * and number of columns, followed by one line per row, followed by the start location, 
 * and ending with the exit location. Each maze location is
 * either a wall (1) or free (0). Here is an example of contents of a file for
 * a 3x4 maze, with start location as the top left, and exit location as the bottom right
 * (we count locations from 0, similar to Java arrays):
 * 
 * 3 4 
 * 0111
 * 0000
 * 1110
 * 0 0
 * 2 3
 * 
 */

public class MazeViewer {
   
    //Representation Invariant
    private static final char WALL_CHAR = '1'; 
    private static final char FREE_CHAR = '0';
    private static MazeCoord startLoc; // start location
    private static MazeCoord exitLoc; // exit location

    public static void main(String[] args)  {

        String fileName = "";

        try {

            if (args.length < 1) {
                System.out.println("ERROR: missing file name command line argument");
            }
            else {
                fileName = args[0];
                
                JFrame frame = readMazeFile(fileName);

                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                frame.setVisible(true);
            }

        }
        catch (FileNotFoundException exc) {
            System.out.println("ERROR: File not found: " + fileName);
        }
        catch (IOException exc) {
            exc.printStackTrace();
        }
    }

    /**
    readMazeFile reads in maze from the file whose name is given and 
    returns a MazeFrame created from it.
   
    @param fileName
             the name of a file to read from (file format shown in class comments, above)
    @returns a MazeFrame containing the data from the file.
        
    @throws FileNotFoundException
              if there's no such file (subclass of IOException)
    @throws IOException
              (hook given in case you want to do more error-checking --
               that would also involve changing main to catch other exceptions)
    */
    private static MazeFrame readMazeFile(String fileName) throws IOException {
        String line = ""; 
        String[] mazeSize;
        int rowNum = 0;
        int numOfRows;
        int numOfCols;
        boolean[][] mazeData;

        //Using BufferedReader reads every line of the text file and set up correct maze format
        BufferedReader buffReader = new BufferedReader(new FileReader(fileName));
        line = buffReader.readLine();
        mazeSize = line.split(" "); 
        numOfRows = Integer.parseInt(mazeSize[0]);//read in number of rows of the maze
        numOfCols = Integer.parseInt(mazeSize[1]);
        mazeData = new boolean[numOfRows][numOfCols];//construct maze data as a 2D array

        //reads values from text file to boolean, representing walls or free  
        while(buffReader.ready()){
            line = buffReader.readLine();
            //construct maze data, for wall => 1, free => 0
            if(rowNum < numOfRows){
                for(int i = 0; i < line.length(); i++){
                  mazeData[rowNum][i] = (line.charAt(i) == WALL_CHAR);
                }
            } 
            //read in start location of the maze
            if(rowNum == numOfRows){
                mazeSize = line.split(" ");
                startLoc = new MazeCoord(Integer.parseInt(mazeSize[0]), Integer.parseInt(mazeSize[1]));
            }
            //read in exit location of the maze
            if(rowNum == numOfRows + 1){
                mazeSize = line.split(" ");
                exitLoc = new MazeCoord(Integer.parseInt(mazeSize[0]), Integer.parseInt(mazeSize[1]));
            }   
                rowNum++;
        }
        return new MazeFrame(mazeData, startLoc, exitLoc);
    }
}