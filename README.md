# open-data-structures
Repo to hold - open data structures work and notes! https://opendatastructures.org


## Table of Contents

1. Introduction

## Introduction
This chapter introduced algorithm assessment and gives a useful map of how the online
textbook should be read.

Check out [this][1] link for a guide on suggested sequence of study.

The directory `/1-intro-exercies/src` contains the exercises completed for this chapter. Found [here][2].

### Toy Java Interfaces to focus on:

```Java
List - simplified List
USet - simplified Set
SSet - simplified sorted set sorted map and map.

Part of the Collections Framework
```
## Array-Based Lists
Implementations of `List` and `Queue` interfaces where underlying is stored in array.

| |`get(i)`/`set(i)`|`add(i,x)`/`remove(i)`|
|-------|-----------------|----------------------|
|`ArrayStack`| O(1) | O(n - i)|
|`ArrayDeque`| O(1) | O(min{i,n - i})|
|`DualArrayDeque`| O(1) | O(min{i,n - i})|
|`RootishArraystack`| O(1) | O(n - i)|

The directory `/2-arraybased-ds/src` contains the exercises completed for this chapter. Found [here][3].

### Notes:
- Arrays offer constant time access to any value.
- Not dynamic: lots of shifting if the middle is being modified.
- No expansion or shrinking - expansion requires whole array copying to new allocation.
Cost of growth is expensive
- If you divide the total cost of resizing `O(m)` by the number of resizing operations `m` you are left with an `O(1)` 
amortized over the long sequence of resizing operations. because they are balanced out by the cheap operations.

<!------- Links ------>
[1]: https://opendatastructures.org/ods-java/1_7_List_Data_Structures
[2]: https://opendatastructures.org/ods-java/1_8_Discussion_Exercises.html
[3]: https://opendatastructures.org/ods-java/2_7_Discussion_Exercises.html

