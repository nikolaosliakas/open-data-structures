/**
 * Similar to SLL except has pointer to prev and next!
 *
 * Many cases to consider when updating a SLL, these get more complex with a Doubly linked list.
 * - Cleanest way to link is to use a dummy node.
 * Check out diagram: <a href="https://opendatastructures.org/ods-java/3_2_DLList_Doubly_Linked_Li.html#fig:dllist">diagram</a>
 *
 * LinkedHashSet - data structure in Java Collections Framework
 */

public class MyDoubleLinkedList <T>{

    class Node<T>{
        T x;
        Node<T> next, prev; // pointer to next and previous nodes
    }


    Node<T> dummy;
    int n;

    MyDoubleLinkedList(){
        dummy = new Node<>();
//        Setting pointers to itself in constructor
        dummy.next = dummy;
        dummy.prev = dummy;
        n = 0;
    }

    Node<T> getNode(int i){
        Node<T> p;
        if (i < n / 2){
            p = dummy.next;
            for(int j = 0; j < i; j++)
                p = p.next;
        } else{
            p = dummy;
            for(int j = n; j > i; j--)
                p = p.prev;
        }
        return p;
    }
    // These operations need to find the i-th node, so are O(1+min{i, n-i})
    T get(int i){
        if (i < 0 || i > n - 1) throw new IndexOutOfBoundsException();
        return getNode(i).x;
    }
    T set(int i, T x){
        if (i < 0 || i > n - 1) throw new IndexOutOfBoundsException();
        Node<T> u = getNode(i);
        T y = u.x;
        u.x = x;
        return y;
    }
    /**
     * Adding - pointers always point to nodes!
     *  - two methods
     *      - addBefore( Node w, T x)
     *          - we want to add before the node w
     *          - we insert a node u beofre w
     *              - set u.next to w
     *              - set u.prev to w.orev
     *              - set u.next.prev to u
     *              - set u.prev.next to u.
     *
     *              | u.prev |  u    | u.next|
     *                /       /^   ^\     \
     *              /       /        \      \
     *       |  |      |    |    | w.prev |  w  | w.next|
     */
    Node<T> addBefore(Node<T> w, T x){
        Node<T> u = new Node<>();
        u.x = x;
        // u.prev = whatver came before w.
        u.prev = w.prev;
        // w is being pointed to next of u
        u.next = w;
        // equivalent to setting w.prev to u;
        u.next.prev = u;
        // THIS is the magic. accessing the previous node's next pointer and updating it!!!
        u.prev.next = u;

        n++;
        return u;
    }

    /**
     * add before when you find the node you want to add before
     * - getNode is non-constant. Updating local pointers is constant.
     */
    void add(int i, T x){
        if(i < 0 || i > n - 1) throw new IndexOutOfBoundsException();
        addBefore(getNode(i), x);
    }

    /**
     * Removal means adjustment of pointers.
     * A B C
     * removal of B
     * A's pointer to B now points to C
     * C's pointer to B now points to A
     *
     * This makes the node unreachable - GC collects these artifacts.
     */
    void remove(Node<T> w){
        w.prev.next = w.next;
        w.next.prev = w.prev;
        n--;
    }

    T remove(int i){
        if(i < 0 || i > n - 1) throw new IndexOutOfBoundsException();
        Node<T> w = getNode(i);
        remove(w);
        return w.x;
    }





}
