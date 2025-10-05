package rest;

import java.util.Arrays;
import java.util.List;

/**
 * Exercise 1..6   From scratch, write and test implementations of the List, USet and SSet interfaces.
 * These do not have to be efficient. They can be used later to test the correctness and performance of
 * more efficient implementations. (The easiest way to do this is to store the elements in an array.)
 * */
public class ListImplementation<T> {
    private T[] l;
    private int size;


    /// Number of times the array has been structurally modified! Really useful for sizing!
    protected transient  int modCount = 0;
    private static final int DEFAULT_CAPACITY = 10;
//    private static final Object[] EMPTY_ELEMENTDATA = {};

    transient T[] elementData;

    @SuppressWarnings("unchecked")
    ListImplementation(int initialCapacity){
        elementData =  (T[]) new Object[initialCapacity];
        size = initialCapacity;
    }

    @SuppressWarnings("unchecked")
    public ListImplementation(){
        elementData =  (T[]) new Object[DEFAULT_CAPACITY];
    }

    public void ensureCapacity(int minCap){
        if(minCap > elementData.length
            && minCap <= DEFAULT_CAPACITY){
            modCount++;
            grow(minCap);
        }
    }

    public T[] grow(int minCapacity){
        int oldCap = elementData.length;
        int newCap = oldCap + minCapacity;
        return elementData = Arrays.copyOf(elementData, newCap);
    }



}
