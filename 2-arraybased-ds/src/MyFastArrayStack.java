/**
 *  * For shifting:
 *  * Other languages have features
 *  *  C   ---> memcpy(d , s, n) and memmove(d , s, n)
 *  *  C++ ---> std::copy(a0, a1, b)
 *  *  Java --> System.arraycopy(s, i, d, k, n)
 * Factor between 2 and 3 time speed up to use the Java implementation rather than looping like in ArrayStac
 */

public class MyFastArrayStack<T> {
    T[] a;
    int n;
    int size(){
        return n;
    }
    @SuppressWarnings("unchecked")
    void resize() {
        T[] b = (T[]) new Object[Math.max(n * 2, 1)];
        System.arraycopy(a, 0, b, 0, n);
        a = b;
    }
    void add(int i, T x) {
        if (i < 0 || i > n) throw new IndexOutOfBoundsException();
        if (n + 1 > a.length) resize();
        System.arraycopy(a, i, a, i+1, n-i);
        a[i] = x;
        n++;
    }
    T remove(int i) {
        if (i < 0 || i > n - 1) throw new IndexOutOfBoundsException();
        T x = a[i];
        System.arraycopy(a, i+1, a, i, n-i-1);
        n--;
        if (a.length >= 3*n) resize();
        return x;
    }
}
