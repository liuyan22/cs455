public class yannnn{

    public static void main(String[] args) {
    
        System.out.println(isPalindrome("radar"));
       
    }
    public static boolean isPalindrome(String word){
           return isPalindromeR(word, 0, word.length());
    }

    public static boolean isPalindromeR(String word, int startLoc, int end){
        int len = startLoc - end;
        if ((len == 1) || (len == 0)) {
           return true;
        }

        if (word.charAt(startLoc) != word.charAt(end -1)){
            return false;
        }
        return isPalindromeR(word, startLoc + 1, end - 1);
        
    


    }
}

