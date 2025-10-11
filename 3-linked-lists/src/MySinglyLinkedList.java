/**
 * Singly Linked Lists
 * - cannot use get(n) and set(n, x) in constant time like an array
 * - efficiently implements queues(add remove) and stacks (push pop)
 * Setting and removing elements from stacks and queues takes O(1)
 *
 * NB - removing from the tail requires n-2 traversals and this is the only Deque (double-ended queue) operation missing from the Singly Linked List.
 * - this is because you will need to update the value of the tail to the node just preceing tail and there is no pointer 'back' to the previous node.
 */
public class MySinglyLinkedList<T>{

    class Node<T>{
        T x;
        Node<T> next;
    }
//    class fields for SinglyLinkedList

    Node<T> head;
    Node<T> tail;
    int n;

    /**
     * Push onto stack
     * 1. create new node
     * 2. assign new node value to arg x
     * 3. it sets u.next to the __old__ head of the list
     *      and makes the new node the head of the list.
     * 4. increment list
     * 5. return value added into new node.
     */
    T push(T x){
        Node<T>  u = new Node<>();
        u.x = x;
        u.next = head;

        head = u;
        if (n == 0)
            tail = u;
        n++;

        return x;
    }
    /**
     * Pop from stack
     * - checks if stack is empty
     * - if-not create a variable to get the value from head.x
     *      - removing the head by subsuming into the .next assignment
     * - if decrementing empties the stack assign the tail to null.
     */

    T pop(){
        if (n == 0) return null;
        T x = head.x;
        head = head.next;
//        if --n then the value is decremented AND then used for condition
        // if n-- this condition will always be false as it was never met from the above condition ie it would be used as n <> 0 ALWAYS if it reaches this ifthen statement.
        if (--n == 0) tail = null;
        return x;
    }

    /**
     * remove
     * identical to pop
     */
    T remove(){
        if (n == 0) return null;
        T x = head.x;
        head = head.next;
//        if --n then the value is decremented AND then used for condition
        // if n-- this condition will always be false as it was never met from the above condition ie it would be used as n <> 0 ALWAYS if it reaches this ifthen statement.
        if (--n == 0) tail = null;
        return x;
    }
    /**
     * Additions are always done on the __tail__ in a Queue
     * - tail is always set to new node
     * - if n == 0 -> the head is also set to u
     * - otherwise set the tail.next to the new node!
     */

    boolean add(T x){
        Node<T> u = new Node<>();
        u.x = x;
        if (n == 0){
            head = u;
        } else{
            tail.next = u;
        }
        tail = u;
        n++;
        return true;
    }



}
