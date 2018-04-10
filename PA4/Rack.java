// Name: Yan Liu    
// USC NetID: liu156
// CS 455 PA4
// Spring 2018

import java.util.ArrayList;
import java.util.*;


/**
 * A Rack of Scrabble tiles.
 */

public class Rack {
   
    //Representation Invariants
    private char[] tiles; //tiles of the rack
    private AnagramDictionary dictionary; //input dictionary
    private ArrayList<String> madeWords; //all the anagrams of all the subsets
   
    public Rack(String data, AnagramDictionary dictionary){
        this.dictionary = dictionary;
        this.tiles = data.toCharArray();
        madeWords = new ArrayList<String>();
        Arrays.sort(tiles);
    }

    /**
     * Find all words can be made for current rack
     */
    public void makeWords(){
        //find mult: the multiplicity of each letter from unique.
        ArrayList<Integer> _mult = new ArrayList<Integer>();
        String unique = "";

        //find unique String
        for (int i = 0; i < tiles.length; i++) {
            if (i != 0 && tiles[i] == tiles[i - 1]) {
                _mult.set(_mult.size() - 1, _mult.get(_mult.size() - 1) + 1);;
            } else {
                unique += tiles[i];
                _mult.add(1);
            }
        }

        //get mult: the multiplicity of each letter from unique.  
        int[] mult = new int[_mult.size()];
        for (int i = 0; i < _mult.size(); i++) {
            mult[i] = _mult.get(i);
        }

        //find subsets from unique string, mult, and the smallest index of unique and mult to consider
        ArrayList<String> subsets = allSubsets(unique, mult, 0);
       
        //find all anagrams to make the words
        for(String subword: subsets) {
            ArrayList<String> anagrams = dictionary.getAnagramsOf(subword);
            this.madeWords.addAll(anagrams);
        }

        //---TESTING: DELETE LATER ---
        for(String word: madeWords) {
            System.out.println(word);
        }
        //----------------------------

    }
    
    /**
     * get number of anagrams 
     * @return number of anagrams of s
     */
    public int numOfAnagrams(){
        return madeWords.size();
    }

    /**
    * Finds all subsets of the multiset starting at position k in unique and mult.
    * unique and mult describe a multiset such that mult[i] is the multiplicity of the char
    *      unique.charAt(i).
    * PRE: mult.length must be at least as big as unique.length()
    *      0 <= k <= unique.length()
    * @param unique a string of unique letters
    * @param mult the multiplicity of each letter from unique.  
    * @param k the smallest index of unique and mult to consider.
    * @return all subsets of the indicated multiset
    * @author Claire Bono
    */
    private static ArrayList<String> allSubsets(String unique, int[] mult, int k) {
        ArrayList<String> allCombos = new ArrayList<>();
      
        if (k == unique.length()) {  // multiset is empty
            allCombos.add("");
            return allCombos;
        }
        
        // get all subsets of the multiset without the first unique char
        ArrayList<String> restCombos = allSubsets(unique, mult, k+1);
        
        // prepend all possible numbers of the first char (i.e., the one at position k) 
        // to the front of each string in restCombos.  Suppose that char is 'a'...
        
        String firstPart = "";          // in outer loop firstPart takes on the values: "", "a", "aa", ...
        for (int n = 0; n <= mult[k]; n++) {   
            for (int i = 0; i < restCombos.size(); i++) {  // for each of the subsets 
                                                            // we found in the recursive call
                // create and add a new string with n 'a's in front of that subset
                allCombos.add(firstPart + restCombos.get(i));  
            }
            firstPart += unique.charAt(k);  // append another instance of 'a' to the first part
        }
        
        return allCombos;
    }

   
}
