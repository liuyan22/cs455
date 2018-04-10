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
    private int[] mult;
    private String rack;
    private ArrayList<Integer> vals;
    private ArrayList<String> subset;
    private Map<String, Integer> rackMap;
    private String sortedString;

    
    public Rack(){
        rack = "";
        //vals = new ArrayList<Integer>();
        mult = new int[100];
        rackMap = new TreeMap<String, Integer>();
        subset = allSubsets(getUniqueStr(), mult, 0);
    }

    /**
     * Read each line from text file, store each character as key and each occurrence as value
     * into the racakMap.
     * @param line input string
     */
    public void addDataToRack(String line){
        vals = new ArrayList<Integer>();
        rack = line.replaceAll("[^a-zA-Z]+", "");
        sortedString = sortString(line);
        int curr = 0;
        for(int i = 0; i < rack.length(); i++){
            if(rackMap.containsKey(String.valueOf(rack.charAt(i)))){
                curr = rackMap.get(String.valueOf(rack.charAt(i)));
                rackMap.put(String.valueOf(rack.charAt(i)), curr + 1);
            }
            else{
                rackMap.put(String.valueOf(rack.charAt(i)), 1);
            }  
        }
        
        for(Map.Entry<String, Integer> val : rackMap.entrySet()){
           vals.add(val.getValue());
        }
        // for(int i = 0; i < vals.size(); i++){
        //     mult[i] = vals.get(i);
        // }
    }

    /**
     * Accessor: get Sorted rack 
     */
    public String getSortedStr(){
        return this.sortedString;
    }

    public ArrayList<Integer> getMult(){
        return this.vals;
    }
    /**
     * Accessor: to get all subsets of a rack
     */
    public ArrayList getSubsets(){
        return this.subset;
    }

    /**
     * Sort the rack aphabetically. 
     * @param string the input String that need to be sorted
     */
    public String sortString(String string){
        char tempArr[] = string.toCharArray();
        Arrays.sort(tempArr);
        return new String(tempArr);
    }             

    public String getRack(){
        return this.rack;
    }
    
    /**
     * Get unique String from the map 
     */
    public String getUniqueStr(){
        String uniqueStr = "";
        for(Map.Entry<String, Integer> value : rackMap.entrySet()){
            uniqueStr  += value.getKey();
        }
        return uniqueStr;
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
