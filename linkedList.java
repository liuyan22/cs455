import java.util.LinkedList;
import java.util.ListIterator;

public class linkedList{

    public static void main(String[] args){
        LinkedList<Integer> list = new LinkedList<Integer>();
        list.add(33);
        list.add(94);
        list.add(56);
        list.add(59);
        
        // for (int i = 0; i < list.size(); i++){
        //     System.out.println(list.get(i));
        // }
        // System.out.println(list.getFirst());

        ListIterator<Integer> iter = list.listIterator();
        // while(iter.hasNext()){
        //     int num = iter.next();
        //     if (num >= 60){
        //     System.out.println(num);
        //     }
        // }
        System.out.println(iter);
        System.out.println(iter.next());
        System.out.println(iter);
        System.out.println(iter.next());

    }
}