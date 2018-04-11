// Name: Yan Liu
// USC NetID: liu156
// CS 455 PA4
// Spring 2018

import java.util.*;

/**
 * A score table of all letters 
 * Note: both upper case and lower case letter has the same score
 */

public class ScoreTable{

    //Representation Invariants
    private int[] scoreTable; //score table

    public ScoreTable(){
        this.scoreTable = new int[26];
        for (char ch = 'a'; ch < 'z' + 1; ch++) {
            if (ch == 'd' || ch == 'g') {
                scoreTable[ch - 'a'] = 2;
            } else if (ch == 'b' || ch == 'c' || ch == 'm' || ch == 'p') {
                scoreTable[ch - 'a'] = 3;
            } else if (ch == 'f' || ch == 'h' || ch == 'v' || ch == 'w' || ch == 'y'){
                scoreTable[ch - 'a'] = 4;
            } else if (ch == 'k'){
                scoreTable[ch - 'a'] = 5;
            } else if (ch == 'j' || ch == 'x'){
                scoreTable[ch - 'a'] = 8;
            } else if (ch == 'q' || ch == 'z'){
                scoreTable[ch - 'a'] = 10;
            }
            else {
                scoreTable[ch - 'a'] = 1;
            }
        }
    }

    /**
     * Print sorted version of words made, according to score/alphabet
     * @param madeWords Arraylist of words 
     */
    public void printSortedAnagrams(ArrayList<String> madeWords){
        //get scores of each madeWord and store string and score to a tree map
        Map<String, Integer> wordsMap = new TreeMap<String, Integer>();
        String word = "";
        int scores = 0;
        for (int i = 0; i < madeWords.size(); i++){
            word = madeWords.get(i);
            scores = getScore(word);
            wordsMap.put(word, scores);
        }
        
        //sort made Words from tiles according to their score, if the scores are the same, then sort them alphabeticlly
        ArrayList<Map.Entry<String, Integer>> wordsMapList = new ArrayList<Map.Entry<String, Integer>>(wordsMap.entrySet());
        Collections.sort (wordsMapList, new Comparator<Map.Entry<String, Integer>>(){
            @Override
            public int compare (Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2){
                
                return o2.getValue() - o1.getValue();
            }
        });      

        //print the words in decreasing order by score 
        for (Map.Entry<String, Integer> val : wordsMapList){
            System.out.println(val.getValue() + ": " + val.getKey());
        }
    }
    
    /**
     * get score of each word
     * @param word input string 
     * @return score of input string 
     */
    public int getScore(String word){
        word = word.toLowerCase();
        int scores = 0;
        for (int i = 0; i < word.length(); i++){
            char temp = word.charAt(i);
            scores += scoreTable[temp - 'a'];
        }

        return scores;
    }
}