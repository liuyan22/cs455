// Name: Yan Liu    
// USC NetID: liu156
// CS 455 PA4
// Spring 2018

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
    public static void main (String[] args){

        //optional command-line argument for the dictionary file name
        String fileName = "sowpods.txt";
        AnagramDictionary dictionary = null;
        try{
            if (args.length > 0){
                fileName = args[0];
            }
            dictionary = new AnagramDictionary(fileName);
        }
        catch (FileNotFoundException e){
            System.out.println("File Not Found " + fileName);
        }

        //print out valid words can be made from the rack of tiles of an input file
        Scanner in = new Scanner(System.in);
        char ch = '"';
        System.out.println("Type . to quit");

        while(in.hasNextLine()){
            String line = in.nextLine();
            if (!".".equals(line)){
                line = line.replaceAll("[^a-zA-Z]+", "");
                Rack rack = new Rack(line, dictionary); 
                ScoreTable table = new ScoreTable();
                String sortedStr = dictionary.sortTheString((line)); //sort the line in alphabetical order
                rack.makeWords();
                System.out.println("Rack? We can make " + rack.numOfMadeWords() + " words from " + ch + dictionary.sortTheString(line) + ch);
                if (rack.numOfMadeWords() > 0){
                    System.out.println("All of the words with their scores (sorted by score): "); 
                    table.printSortedAnagrams(rack.getMadeWords());
                }
            }
            else{
                System.out.println("Rack?");
                System.exit(0);
            }  
        }
            
    }

}