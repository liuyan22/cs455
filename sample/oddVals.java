import java.util.*;

public class oddVals{
    
    public static void main(String[] args){
        ArrayList<Integer> values = new ArrayList<Integer>();
        values.add(5);
        values.add(3);
        values.add(10);
        values.add(3);

        ArrayList<Integer> f = oddVals(values);
        for(int val : values){
            System.out.println(val);
        }

        for(int val : f){
            System.out.println(val);
        }
        
    }
    // 1. Defination: Given a list of numbers(vals), return a list of its odd numbers
    public static ArrayList<Integer> oddVals(ArrayList<Integer> vals){
        // 3. Base case - the case that does not have a previosu state;
        if(vals.isEmpty()){
            return vals;
        }
        
        /**
         2: Recursive part: 
         if the last one is odds, then the result should the be the odds from [0..n-1] and n itself
         else , the last is ignored, simply return the odds from [0..n-1]
        */ 
        int last = vals.get(vals.size() - 1);
        ArrayList<Integer> previous = vals.subList(0, vals.size() - 1);
        ArrayList<Integer> result = oddVals(previous);

        if(last % 2 != 0) { // only append if the last is [ODD.] 
            result.add(last);
        }
        return result;




        
        
    }

}