public class recursion{

    public static void main(String[] arg){
        System.out.println(fastExpon(2,3));
        
    }

    public static int fastExpon(int x, int n){
        if (n == 1){
            return x;
        } 
        if (n ==0){
            return 1;
        }
        if (n%2 == 0) 
        {
            int a = fastExpon(x, n/2);
            return a * a;
        }
        else {
            return x * fastExpon(x, (n-1));
        }
    }
}