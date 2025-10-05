import java.util.NoSuchElementException;

/**
 * FIFO Queue
 * - ArrayStack is a poor choice as Queuing requires working on both ends to remove from one and add from another
 *      this will result in either remove or add costing n operations as each value is shifted in the array.
 *
 * What if we had an infinite array
 *  - j is the next element to remove and n tells us the length of the array.
 *      [null, null, null] (j=0, n=0)- j and n == 0 initially
 *      [x, null, null] (j=0, n=1) - add       == place at a[j + n], increment n
 *      [x,y,null] (j=0, n=2) - add
 *      [x, y, z] (j=0, n=3) - add
 *      [null, y, z](j=1,n=2)- remove    == remove from a[j], increment j, decrement n
 *
 *
 *  ArrayQueue - uses modular arithmetic to simulate infinity!
 *      - like time of day 3 pm = 10 + 5 = 15 congruent 3 (mod 12)
 */
public class MyArrayQueue<T> {
    T[] a;
    int j;
    int n;


    boolean add(T x) {
        if (n + 1 > a.length) resize();
//        Modular arithmetic used to get the location of next add which is at the tail of the queue
        a[ (j + n) % a.length] = x;
        n++;
        return true;
    }

    /**
     * Removes element from head of queue
     * - j tracks the index for the head of the queue
     * - increment that index by 1 with the modular arithmetic.
     * - decrement n
     * - if length of array is gte 3 times n resize.
     */
    T remove(){
        if (n == 0) throw new NoSuchElementException();

        T x = a[j];
        j = (j + 1) % a.length;
        n--;
        if(a.length >= 3*n) resize();
        return x;
    }

    @SuppressWarnings("unchecked")
    void resize(){
        T[] b = (T[]) new Object[Math.max(n * 2, 1)];
        for(int k = 0; k < n; k++)
            b[k] = a[(j + k) % a.length];
        a = b;
        j = 0;
    }
}
