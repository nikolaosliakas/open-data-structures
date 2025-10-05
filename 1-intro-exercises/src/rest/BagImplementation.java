package rest;

import ods.USet;
import ods.USetSet;

import java.util.*;

/**
 *Using a USet, implement a Bag. A Bag is like a USet--it supports the $ \mathtt{add(x)}$, $ \mathtt{remove
 * (x)}$ and $ \mathtt{find(x)}$ methods--but it allows duplicate elements to be stored. The $ \mathtt{find
 * (x)}$ operation in a Bag returns some element (if any) that is equal to $ \mathtt{x}$. In addition, a
 * Bag supports the $ \mathtt{findAll(x)}$ operation that returns a list of all elements in the Bag that
 * are equal to $ \mathtt{x}$.
 * */
public class BagImplementation<T> implements USet<T> {

    List<Entry> bag;
    List<T> s;
    class Entry implements Map.Entry<T, Integer> {
        T k;
        Integer v;

        Entry(T k, Integer v){
            this.k = k;
            this.v = v;
        }
        @Override
        public T getKey() {return k;}

        @Override
        public Integer getValue() {return v;        }

        @Override
        public Integer setValue(Integer value) { return v = value;}

        public boolean equals(Entry e) {
            return e.k.equals(k);
        }
        @Override
        public int hashCode() {
            return k.hashCode();
        }
    }


    int n; // num of elements in bag

//    Constructor
    BagImplementation(){
        bag = new ArrayList<>();
        n = 0;}


    @Override
    public int size() {
        return n;
    }

    @Override
    public boolean add(T x) {
        return false;
    }


//    @Override
//    public boolean add(T x) {
//        Entry y = findEntry(x);
//        // If value isn't found in set add new entry
//        if(Objects.isNull(y)){
//            Entry newEntry = new Entry(x, 1);
//            bag.add(newEntry);
//        } else{
//
//        }
//        // else increment value of entry
//        return tru
//    }

    @Override
    public T remove(T x) {
        return null;
    }

    @Override
    public T find(T x) {
        return Objects.isNull(findEntry(x)) ? x : null;
    }
    private Entry findEntry(T key){
        for (Entry y : bag)
            if(y.getKey() == key)
                return y;
        return null;

    }

    @Override
    public void clear() {

    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
