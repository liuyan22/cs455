// Name: Yan Liu
// USC NetID: liu156
// CS 455 PA4
// Spring 2018

import java.util.*;

/**
 * A score table of all letters 
 * Note: both upper case and lower case letter count
 */

public class ScoreTable{

    //Representation Invariants
    private int[] score; //score table

    public ScoreTable(){
        int[] score = new int['z' + 1];
    }

    
    public ArrayList<String> sortAnagrams(ArrayList<String> madeWords){
        score['a'] = score['e'] = score['i'] = score['o'] = score['u'] = score['l'] = score['n'] = score['s'] = score['t'] = score['r'] = 1;
        score['d'] = score['g'] = 2; //assign value to score at index 'd' & 'g'.
        score['b'] = score['c'] = score['m'] = score['p'] = 3;
        score['f'] = score['h'] = score['v'] = score['w'] = score['y'] = 4;
        score['k'] = 5;
        score['j'] = score['x'] = 8;
        score['q'] = score['z'] = 10;

        //get scores of each madeWord
        Map<String, Integer> wordsMap = new TreeMap <String, Integer>();
        int scores = 0;
        String word = "";
        for(int i = 0; i < madeWords.size(); i++){
            word = madeWords.get(i);
            char[] temp = word.toCharArray();
            for(int j = 0; j < temp.length; j++){
                char ch = temp[j];
                scores = scores + score[ch - 'a'];
            }
            wordsMap.put(word, score);
        }
        
        //sort made Words from tiles according to their score, if the scores are the same, then sort them alphabeticlly
        ArrayList<Map.Entry<String, Integer>> wordsMapList = new ArrayList(wordsMap.entrySet());
        Collections.sort(madeWords, new Comparator<Map.Entry<String, Integer>>(){
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2){
                if(o1.getValue() != o2.getValue()){
                    return o2.getValue() - o1.getValue();
                }
                return o2.getKey().compareTo(o1.getKey());
            }
        });      

        ArrayList<String> sorted = new ArrayList<String>();
        for(Map.Entry<String, Integer> val : wordsMapList){
            sorted.add(val.getValue());
        }
        return sorted;
    }
    
    
}