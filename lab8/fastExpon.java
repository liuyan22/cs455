public class fastExpon{
    
    public static void main(String[] args){

        System.out.println(fastExpon(2,3));

    }

    public static int fastExpon(int x, int n){
        if (n == 1){
            return x;
        }
        else{
            int a = fastExpon(x, 1/2 * n);
            return a * a;
        }
        
            

        
    }
}



     