import java.util.*;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.IOException;

/**
    This program reads the user input file containing one or multiple Strings, and prints out all
    anagrams of the valid inputs with legal letters only by either decreasing order of Scrabble score 
    or decreasing alphabetical order
    If the file doesn't exits, an error message is displayed
 */
public class WordFinder{
    public static void main(String[] args){
        try{
            Scanner in = new Scanner(System.in);
            AnagramDictionary dictionary = new AnagramDictionary("sowpods.txt"); 
            Rack rack = new Rack();
            System.out.println("Type . to quit");
            //Read data to rack
            while(in.hasNextLine()){
                String line = in.nextLine();
                if(!line.equals(".")){
                    rack.addDataToRack(line);
                     System.out.println(rack.getRack());
                    System.out.println(rack.getMult());    
                }
                else{
                    System.exit(0);
                }  
            }
                 System.out.println(rack.getRack());
        System.out.println(rack.getMult());
            
        }
        catch(FileNotFoundException e){
            System.out.println("File Not Found");
        }
   
        
    }
    
}