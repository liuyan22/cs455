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
        String fileName = "sowpods.txt";
        AnagramDictionary dictionary = null;
        //optional command-line argument for the dictionary file name
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
        //Read data to rack
        while(in.hasNextLine()){
            String line = in.nextLine();
            if(line != "."){
                line = line.replaceAll("[^a-zA-Z]+", "");
                Rack rack = new Rack(line);
                System.out.println("Rack? " + line);
                System.out.println("We can make " + dictionary.numOfAnagrams(rack.sortString(line)) + " " + "words from" + " " + rack.sortString(line));
                printAnagrams(rack.sortString(line), dictionary);
            }
            else{
                System.exit(0);
            }  
        }
            
    }

    /**
     * print all anagrams from given rack
     * @param line input string
     * @param dictionary dictionary
     */
    public static void printAnagrams(String line, AnagramDictionary dictionary){
        System.out.println("All of the words with their scores (sorted by score)");
        ArrayList<String> anagrams = new ArrayList<String>();
        anagrams = dictionary.getAnagramsOf(line);
        if(anagrams != null){
            for(int i = 0; i < anagrams.size(); i++){
                System.out.println(anagrams.get(i));
            }
        }
    }
}