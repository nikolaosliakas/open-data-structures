package rest;

import java.util.ArrayDeque;

import java.util.Deque;
import java.util.Queue;

public class ReverseStack {


    /**
     * Suppose you have a Stack, s, that supports only the push(x) and pop() operations. Show how using only a FIFO Queue q you can reverse the order of all elements in s.
     *
     * */
    public static void reverseStackUsingQueue(){
        Deque<Integer> intStack = new ArrayDeque<>();
        intStack.add(5); // bottom
        intStack.add(4);
        intStack.add(3);
        intStack.add(2);
        intStack.add(1); // top

        Integer i;
        Queue<Integer> intQueue = new ArrayDeque<>();
        while((i = intStack.poll()) != null){
            intQueue.offer(i);
        }
        while((i= intQueue.poll()) != null){
            intStack.push(i);
        }
        System.out.println("After reversal: ");
        while((i= intStack.poll()) != null){
            System.out.print(i + "   ");
        }
    }

}
