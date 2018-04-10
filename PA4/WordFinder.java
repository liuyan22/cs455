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
        //optional command-line argument for the dictionary file name
        String fileName = "sowpods.txt";
        AnagramDictionary dictionary = null;
        try{
            if(args.length > 0){
                fileName = args[0];
            }
            dictionary = new AnagramDictionary(fileName);
        }
        catch(FileNotFoundException e){
            System.out.println("File Not Found" + fileName);
        }
   
        Scanner in = new Scanner(System.in);
        System.out.println("Type . to quit");
        while(in.hasNextLine()){
            String line = in.nextLine();
            if(line != "."){
                line = line.replaceAll("[^a-zA-Z]+", "");
                Rack rack = new Rack(line, dictionary); 
                System.out.println("Rack? " + line);
                System.out.println("We can make " + rack.numOfAnagrams() + " from " + dictionary.sortTheString(line));
                System.out.println("All of the words with their scores (sorted by score): ");
                rack.makeWords();
            }
            else{
                System.exit(0);
            }  
        }
            
    }

}