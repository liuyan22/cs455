import java.io.PrintStream;
import java.util.Map;
import java.util.TreeMap;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.Iterator;

public class Concord{
    Map<String, Integer> concordMap;
    Iterator<String> itr = concordMap.keySet().iterator();
    public Concord(){
        concordMap = new HashMap<>();
    }

    public addData(Scanner in){
        while(in.hasNext()){
            String word = in.next();
            if(concordMap.containsKey(word)){
                int curr = concordMap.get(word);
                concordMap.put(word, curr + 1);
            }
            else {
                concordMap.put(word, 1);
            }
        }
    }

    public String toString(){
        return concordMap.toString();
    }
}