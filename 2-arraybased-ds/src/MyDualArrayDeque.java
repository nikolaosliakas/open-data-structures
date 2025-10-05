import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * How to make complex data structures by combining simpler ones
 *
 *  - 2 ArrayStacks front and back
 *  - front stores in reverse order AKA both are pushing and popping from the max index.
 *  [5, 6 , 7, 8, 9] [10, 11, 12, 13]
 *            <----- ----->
 *
 * front: [5, 6 , 7, 8, 9]
 * back: [10, 11, 12, 13]
 */
public class MyDualArrayDeque<T> {

    List<T> front;
    List<T> back;

    int size(){
        return front.size() + back.size();
    }

    /**
     *  [5, 6 , 7, 8, 9] [10, 11, 12, 13]
     *
     * front: [5, 6 , 7, 8, 9]
     * back: [10, 11, 12, 13]
     *
     * get(4)
     *  - i is less than front.size()
     *      - index into front with .get()
     *      - input into front.get() is :
     *                          size of front = 5;
     *                          - (i=4);
     *                          - 1
     *                          == 0
     *      - at index 4 is value 9
     *      - but the order of retrieval is right-to-left!
     *      - so the front.get(0) == 9
     * get(7)
     * - i is gte front.size()
     * - NB back stores left to right
     *
     */
    T get(int i){
        if (i < front.size()){
            return front.get(front.size() - i - 1);
        } else{
//            NOTE here i - front.size indexes INTO back with the remainder of the subtraction
            return back.get(i - front.size());
        }
    }

    T set(int i, T x){
        if (i < front.size()){
            return front.set(front.size() - i - 1, x);
        } else{
//            NOTE here i - front.size indexes INTO back with the remainder of the subtraction
            return back.set(i - front.size(), x);
        }
    }

    void add(int i, T x){
        if (i < front.size()){
//            Note there is no -1 here as we are not retrieving but PLACING values
//            This is because we are adding it to the RIGHT of the index stated and shifting all other right values to the right by one (increment to right)
//            It maps it to the index AFTER teh addition has been made!
            front.add(front.size() - i, x);
        } else{
//            NOTE here i - front.size indexes INTO back with the remainder of the subtraction
            back.add(i - front.size(), x);
        }
        balance();
    }

    T remove(int i){
        T x;
        if (i < front.size()){

            x = front.remove(front.size() - i -1);
        } else{
//            NOTE here i - front.size indexes INTO back with the remainder of the subtraction
            x = back.remove(i - front.size());
        }
        balance();
        return x;
    }

    /**
     * Ensures front and back do not become too big or too small and is called after each update!
     *  - it guarantees that at least n/4 are located in each stack!
     *      - if not it puts n/2 in one and n/2 in another.
     */
    void balance(){
        int n = size();

//        if 3 times the size is less AKA less than one quarter in front.
        if (3 * front.size() < back.size()){
//            n=30; back.size()=26; front.size()=4; s=11;
            int s = n/2 - front.size();

            List<T> l1 = newStack();
            List<T> l2 = newStack();

            l1.addAll(back.subList(0,s)); // take 11(0 to 10) vals from back 0, 1, 2, 3, 4, 5
            Collections.reverse(l1); // reverse 5, 4, 3, 2, 1, 0

            l1.addAll(front); // append teh rest of front(-3, -4, -5, -6): -3, -4, -5, -6, 5, 4, 3, 2, 1, 0
            l2.addAll(back.subList(s, back.size()));// slice into from s=11 to end of back(11- back.size())

            front = l1;
            back = l2;


        }else if (3 * back.size() < front.size()){
            int s = front.size() - n/2;
            List<T> l1 = newStack();
            List<T> l2 = newStack();

//            Slice the front list
            /*
            * s = 9 - (11/2) = 4
            * Logically teh deque looks like:
            * [2,3,4,5,6,7,8,9,10,0,1]
            * front = [10,9,8,7,6,5,4,3,2] = size 9 (physical) storage
            * back = [0,1] = size 2 (physical and logical storage)
            * */
            l1.addAll(front.subList(s, front.size())); // 4 to 9(exc) == [10,9,8,7,6]
            l2.addAll(front.subList(0, s)); // 0 to 4(exc) == [2, 3, 4, 5]
            Collections.reverse(l2); // [5, 4, 3, 2]

            l2.addAll(back); // [5, 4, 3, 2, 0, 1]
            front = l1;
            back = l2;
        }

    }
    protected List<T> newStack() {
        return new ArrayList<T>();
    }



}
