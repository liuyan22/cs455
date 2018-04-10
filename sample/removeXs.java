import java.util.*;

public class removeXs{

    public static void main(String[] args){
        String word = "xabcxyzxx";
        System.out.println(removeX(word));
    }

    private static String removeX(String words){
        if(words.isEmpty()){
            return words;
        }

        String previousStr = words.substring(0, words.length() - 1);
        if(words.charAt(words.length() - 1) == 'x'){
            return removeX(previousStr);
        }
        else {
            return removeX(previousStr) + words.charAt(words.length() - 1);
        }

    }
    
}