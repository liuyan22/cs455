// Name: Yan Liu
// USC NetID: liu156
// CS 455 PA4
// Spring 2018

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.*;
import java.util.*;


/**
 * A dictionary of all anagram sets. 
 * Note: the processing is case-sensitive; so if the dictionary has all lower
 * case words, you will likely want any string you test to have all lower case
 * letters too, and likewise if the dictionary words are all upper case.
 */

public class AnagramDictionary {
   
    //Representation Invariants
    private Map<String, String> dictionary; //data in dictionary is store in Map

   /**
    * Create an anagram dictionary from the list of words given in the file
    * indicated by fileName.  
    * PRE: The strings in the file are unique.
    * @param fileName  the name of the file to read from
    * @throws FileNotFoundException  if the file is not found
    */
   public AnagramDictionary(String fileName) throws FileNotFoundException {
       try{
            Scanner input = new Scanner(new File(fileName));
            dictionary = new HashMap<String, String>();
            String line = "";
            //read dictionary file into an ArrayList
            while(input.hasNextLine()){
                String line = in.nextLine();
                String sortedLine = sortString(line);
                dictionary.put(sortedLine, line);
            }
        }
       catch (FileNotFoundException exception){
           System.out.println("File Not Found" + fileName);
       }
       
   }
   

   /**
    * Get all anagrams of the given string. This method is case-sensitive.
    * E.g. "CARE" and "race" would not be recognized as anagrams.
    * @param s string to process
    * @return a list of the anagrams of s
    * 
    */
   public ArrayList<String> getAnagramsOf(String s) {
       return new ArrayList<String>(); // DUMMY CODE TO GET IT TO COMPILE
   }
   
   
}
