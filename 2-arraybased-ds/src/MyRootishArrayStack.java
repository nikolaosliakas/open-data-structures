import java.lang.reflect.Array;
import java.util.List;

/**
 * the other structures do not make use of space efficiently
 * - this one does!
 * Stores elements using O(sqrt(n)) arrays.
 *  - At most O(sqrt(n)) Arrays are left empty (much smaller to the other implementations.
 *
 *  Elements are stored in a list of r arrays
 *      - each is called a block number 0, 1, ..., r-1
 *      - Block b contains b + 1 elements
 *
 *  [] [] [] [] []
 *
 *  [1]  [ 2| ]  [3 |  | ]  [4  |  |  |  ] ..
 *
 *  Main problem - which block contains i from the overall index and then the internal index of i in each block!
 *
 *  j = i - b(b + 1) /2 within block b.
 *  b[j] == index needed
 *
 *  A bunch of math to give us the smallest integer b such that
 *
 *      b >= (-3 + sqrt(9 + 8i) / 2
 *      b = ceiling [  (-3 + sqrt(9 + 8i) / 2 ]
 *
 * A long discussion on computing logs and sqrts efficiently for natural numbers up to some large 2^32
 * @see <a href="https://opendatastructures.org/ods-java/2_6_RootishArrayStack_Space.html">.2_6_RootishArrayStack_Space</a>
 */
public class MyRootishArrayStack<T> {

    List<T[]> blocks;
    int n;

    /**
     * get the block from an index
     */
    int i2b(int i){
        double db = (-3.0 + Math.sqrt(9 + 8*i)) / 2.0;
        int b = (int) Math.ceil(db);

        return b;
    }
    Class<T> t;

    /**
     * Both get and set run in constant time!
     */
    T get(int i){
        if (i < 0 || i > n -1) throw new IndexOutOfBoundsException();
        int b = i2b(i);
        int j = i - b * (b + 1)/2;

        return blocks.get(b)[j];
    }
    T set(int i, T x){
        if (i < 0 || i > n -1) throw new IndexOutOfBoundsException();

        int b = i2b(i);
        int j = i - b * (b + 1)/2;
        T y = blocks.get(b)[j];
        blocks.get(b)[j] = x;
        return y;
    }
    @SuppressWarnings({"unchecked"})
    protected T[] newArray(int n) {
        return (T[]) Array.newInstance(t, n);
    }
    void grow() {
        blocks.add(newArray(blocks.size()+1));
    }
    T remove(int i) {
        if (i < 0 || i > n - 1) throw new IndexOutOfBoundsException();
        T x = get(i);
        for (int j = i; j < n-1; j++)
            set(j, get(j+1));
        n--;
        int r = blocks.size();
        if ((r-2)*(r-1)/2 >= n)    shrink();
        return x;
    }
    /*
    * Notice - grow and shrink do not copy any data they allocate or free space
    * */
    void shrink() {
        int r = blocks.size();
        while (r > 0 && (r-2)*(r-1)/2 >= n) {
            blocks.remove(blocks.size()-1);
//            blocks.removeLast();
            r--;
        }
    }
}
