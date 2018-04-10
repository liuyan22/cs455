import java.util.*;
import java.util.Arrays;

public class stack{

    public static void main(String[] args){
        Stack s = new Stack();
        s.push(3);
        s.push(0);
        for (int vals : s.getStackArray()){
            System.out.println(vals);
         }
        s.pop();
        for (int vals : s.getStackArray()){
            System.out.println(vals);
        }
        System.out.print(s.top());

    }

    public static class Stack{
        private int size;
        private int[] values;
        private int lastIndex;

        public Stack(){
            size = 1;
            values = new int[1];
            lastIndex = -1;

        }

        public void push(int input){
            if(lastIndex == size - 1){
                size *= 2;
                values = Arrays.copyOf(values, size);
            }
            lastIndex += 1;
            values[lastIndex] = input;
        }

        public int pop(){
            if(!isEmpty()){
                return -1;

            }
            int lastElement = values[lastIndex];
            lastIndex--;
            return lastElement;
        }

        public int top(){
            return values[lastIndex];
        }

        public boolean isEmpty(){
            return  lastIndex < 0;
        }

        public int[] getStackArray(){
            return values;
        }
    }

}