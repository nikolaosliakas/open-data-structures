/**
 * ArrayStack is an efficient way of implementing a Stack
 * - push(x) == add(n,x)
 * - pop() == remove(n-1)
 * - these ops will move in O(1)
 */
public class MyArrayStack<T> {

    T[] a;
    int n;
    int size(){
        return n;
    }

    /*
    * get and set are pretty straight forward with indexing
    *
    *  - note: set the value and index i is retrieved and temporarily stored. x is then written to index
    * i in the array.
    * */
    T get(int i){
        if(i < 0 || i > n - 1) throw new IndexOutOfBoundsException();
        return a[i];
    }
    T set(int i, T x){
        if (i < 0 || i > n -1) throw new IndexOutOfBoundsException();
        T y = a[i];
        a[i] = x;
        return y;
    }

    /**
     *adds element at index i
     * start at the end of the array and move back until you reach the index
     *  - shift each value at index j.
     *  - when you reach index i place x at the newly vacated index
     *
     * - increment the length field in obj.
     *
     * COST exl - resize is the number of element swe need to shift
     *  O(n-i)
     */
    void add(int i, T x){
        if (i < 0 || i > n -1) throw new IndexOutOfBoundsException();
        if (n + 1 > a.length) resize();
        for(int j = n; j > i; j--)
            a[j] = a[j-1];
        a[i] = x;
        n++;
    }

    /**
     * retrieve value at i and return it as var x
     *  - loop from i to the end of the array
     *  - shift each value to the left (a[j] = a[j+1])
     *  - decrement length n field.
     */
    T remove(int i){
        if (i < 0 || i > n -1) throw new IndexOutOfBoundsException();
        T x = a[i];
        for(int j = i; j < n-1; j++)
            a[j] = a[j+1];
        n--;
        if (a.length >= 3*n) resize();
        return x;
    }

    /**
     * resize array when it needs to grow.
     * - called during addition
     * */
    @SuppressWarnings("unchecked")
    void resize(){
        T[] b = (T[]) new Object[Math.max(n * 2, 1)];
//        a = sourceArray, 0 = srcArrayIndex, b = targetArray, b=targetArrayIndex, n=number of array elements to copy
//        if (n >= 0) System.arraycopy(a, 0, b, 0, n);
        for (int i = 0; i < n; i++){
            b[i] = a[i];
        }
        a = b;
    }

}

