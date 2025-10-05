/**
 * Add or remove from BOTH ends efficiently.
 * Index is checked if it is in the upper or lower half of the array.
 * - modular arithmetic still used.
 *
 * a[ (j + i) % a.length]
 * - the head shifted forward by the index
 */
public class MyArrayDeque<T>{

    T[] a;
    int j;
    int n;

    T get(int i){
        if (i < 0 || i > n -1) throw new IndexOutOfBoundsException();
        return a[ (j + i) % a.length];
    }
    T set(int i, T x){
        if (i < 0 || i > n -1) throw new IndexOutOfBoundsException();
        T y = a[(j + i) % a.length];
        a[ (j + 1) % a.length] = x;
        return y;
    }

    void add(int i, T x) {
//        NB - n is the last index NOT the array size.
        if (i < 0 || i > n) throw new IndexOutOfBoundsException();
//        if last index is abbutting the array size
        if (n+1 > a.length) resize();
//        if i is in the bottom half of the stored values from j to n
        if (i < n/2) { // shift a[0],..,a[i-1] left one position
//            This 'wraps' the array back if j is already 0.
            j = (j == 0) ? a.length - 1 : j - 1; //(j-1)mod a.length
//            Increment upward until i
            for (int k = 0; k <= i-1; k++)
                a[(j + k)%a.length] = a[(j + k+1)%a.length];
//        else in the top half of array
        } else { // shift a[i],..,a[n-1] right one position
//            decrement downward until i - shifting until you reach modular arithmetic evaluated index
            for (int k = n; k > i; k--)
                a[(j + k)%a.length] = a[(j+k-1)%a.length];
        }
//        add value at vacated index
        a[(j+i)%a.length] = x;
//        increment new end of values
        n++;
    }

    T remove(int i){
        if (i < 0 || i > n) throw new IndexOutOfBoundsException();
//        retrieve modular defined i
        T y = a[(j + i) % a.length];

        if(i < n/2){
            j = (j==0) ? a.length - 1: j - 1;
//            Shift right up to i
            for (int k = i; k > 0; k--)
                a[(j + k) % a.length] = a[(j + k - 1) % a.length];
        }
        else{
//            shift left
            for(int k = i; k < n-1; k++)
                a[(j + k) % a.length] = a[(j + k + 1) % a.length];
        }


        if(a.length >= 3*n) resize();
        n--;
        return y;
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
