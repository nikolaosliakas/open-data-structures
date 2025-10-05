# Answers to questions


## 1. In the ArrayStack implementation, after the first call to $ \mathtt{remove(i)}$, the backing array, $ \mathtt{a}$, contains $ \ensuremath{\mathtt{n}}+1$ non- $ \mathtt{null}$ values despite the fact that the ArrayStack only contains $ \mathtt{n}$ elements. Where is the extra non- $ \mathtt{null}$ value? Discuss any consequences this non- $ \mathtt{null}$ value might have on the Java Runtime Environment's memory manager. 

Answer:
    - The ArrayStack logically has bounded itself with a new decremented `--n`. The data structure will not access the old `a[n]`. BUT `a` is still an active datastructure in memory for the program.
    - Consequences for the Java Runtime Environment for this __stale reference__:
1. Prevents Garbage Collections - `a[n-1]` still can be referenced even though it is _logically_ outside of ArrayStack's datastructure bounds. The GC cannot reclaim the data.
2. MEmory Leak (Retention) - not a traditional memory leak AKA _unreachable_ and _uncollected_, but an unnecessary object retention. If large or keeps other memory items active this could retain a lot of memory.

`ArrayList` implements a null to the stale reference! `a[n-1] = null;`

## 2. The List method `addAll(i,c)` inserts all elements of the Collection `c` into the list at position `i`. (The $ \mathtt{add(i,x)}$ method is a special case where $ \ensuremath{\mathtt{c}}=\{\ensuremath{\mathtt{x}}\}$.) Explain why, for the data structures in this chapter, it is not efficient to implement $ \mathtt{addAll(i,c)}$ by repeated calls to $ \mathtt{add(i,x)}$. Design and implement a more efficient implementation.

- `addAll()` commits an entire shift at index i rather than repeatedly shifting all values one increment at a time. `addAll()` can calculate the offset for the shift as one integer rather than repeated calls.
- `n` old positions `k` number of movements needed
  - you have O(n*k) for repeated adds
  - you have O(n + k) for bulk shift with addAll

```java
boolean addAll(int i, Collections<? extends T> c){
//    check for indexOutOfBounds
    int k = c.size();
    
//    Resize if the bulk add will go beyond the bounds of the array
    if(n+k > a.length) resize(2 * (n + k));
    
//    Move elements to the right by k positions
//    YOU MUST iterate backwards as you will be overwriting before reading the value!
    // the last element to be assigned to is the closest to i + k
    for (int j = n + k - 1; j >= i +k; j--)
        a[j] = a[j-k];
    
//    Copy elements into freed space
    for (T x : c)
        a[i++] = x;
    
    n += k;
    return true;
}
```