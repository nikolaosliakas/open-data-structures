package exercises1_1;


import ods.ArrayQueue;
import ods.ChainedHashTable;
import ods.Factory;
import ods.USet;

import java.io.*;
import java.util.*;

public class ReadFileMethods {
    //    Stack is threadsafe - used ArrayDeque for single-threaded implementations of a stack.
//          It also accesses elements in constant time!
//          Can be used as LIFO or FIFO and is a re-sizeable array
    private Deque<String> inputStack = new ArrayDeque<>();
    private Queue<String> inputQueue = new ArrayDeque<>();
    private final String filePath;

    public ReadFileMethods(String filePath) {
        this.filePath = filePath;
    }

    public void ex1_writeInReverse() {

        try (BufferedReader fileReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = fileReader.readLine()) != null) {
                this.inputStack.push(line);
            }
        } catch (IOException e) {
            System.out.println("File not found.");
        }

        System.out.println("Iterating from last element pushed onto ArrayDeque");
//        for(Iterator i = inputStack.iterator(); i.hasNext();){
        for (String s : inputStack) {
            System.out.println(s);
        }
    }

    /**
     * Read the first 50 lines of input and then write them out in reverse order. Read the next 50 lines and then write them out in reverse order. Do this until there are no more lines left to read, at which point any remaining lines should be output in reverse order.
     *
     */
    public void ex2_write50sInReverse(int iterLimit) {


        try (BufferedReader fileReader = new BufferedReader(new FileReader(filePath))) {
            while (true) {
                inputStack.clear();
                int count = 0;
                String line;
                while (
                        count < iterLimit && (line = fileReader.readLine()) != null) {
                    this.inputStack.push(line);
                    count++;
                }
                System.out.println("Iterating from last element pushed onto ArrayDeque of size: " + iterLimit);
                //        for(Iterator i = inputStack.iterator(); i.hasNext();){
                for (String s : inputStack) {
                    System.out.println(s);
                }
                if (count < iterLimit)
                    break;
            }
        } catch (IOException e) {
            System.out.println("File not found.");
        }


    }


    /**
     * Read the input one line at a time. At any point after reading the first 42 lines, if some line is blank
     * (i.e., a string of length 0), then output the line that occured 42 lines prior to that one. For example,
     * if Line 242 is blank, then your program should output line 200. This program should be implemented so that
     * it never stores more than 43 lines of the input at any given time.
     * <p>
     * - keep track of tail of queue.
     * - if head is blank string len =0 then output tail
     * - if head keep queue constant at numLimit FIFO
     *          - remove() returns head of queue
     *          - add(line) == addLast(line) at the tail of the queue
     */
    public void ex3_writeNumLines(int numLimit) {
        inputQueue.clear();
        try (BufferedReader fileReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = fileReader.readLine()) != null) {
                if (inputQueue.size() == numLimit){
                    inputQueue.remove();
                }
                if(line.isEmpty()){
                    System.out.println(inputQueue.remove());
                } else{
                this.inputQueue.add(line);}
            }
        } catch (IOException e) {
            System.out.println("File not found.");
        }
    }

    /**
     *Read the input one line at a time and write each line to the output if it is not a duplicate of some
     * previous input line. Take special care so that a file with a lot of duplicate lines does not use more
     * memory than what is required for the number of unique lines.
     * - Chained Hashtable has O(1) for finding and O(1) add and remove
     * */
    public void ex4_writeDistinct() {

        USet<String> wordSet = new ChainedHashTable<>();
        try (BufferedReader fileReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = fileReader.readLine()) != null) {
                // .add returns true if line is not in set so can be added, false if it is already in the set
//                  Takes O(1)
                if(wordSet.add(line))
                    System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("File not found.");
        }

    }

    /**
     *Read the input one line at a time and write each line to the output only if you have already read
     * this line before. (The end result is that you remove the first occurrence of each line.)
     * Take special care so that a file with a lot of duplicate lines does not use more memory than what
     * is required for the number of unique lines.
     * */
    public void ex5_writeDistinct() {

        USet<String> wordSet = new ChainedHashTable<>();
        try (BufferedReader fileReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = fileReader.readLine()) != null) {
                if(!wordSet.add(line))
                    System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("File not found.");
        }
    }
    /**
    * Read the entire input one line at a time. Then output all lines sorted by length, with the shortest lines
     * first. In the case where two lines have the same length, resolve their order using the usual ``sorted
     * order.'' Duplicate lines should be printed only once.
     * ex6
     * - create a priority queue aKA heap by length
     * - in each member of the queue order by a-z for string and 0-9 for ints
     *  - sub queue is also a heap! both are heaps???
     * */
    /**
     * Do the same as the previous question except that duplicate lines should be printed the same number of
     * times that they appear in the input.
     * ex7
     * */
    /**
     * Read the entire input one line at a time and then output the even numbered lines (starting with the
     * first line, line 0) followed by the odd-numbered lines.
     *
     * - two queues: one even one odd.
     * - output all from even then all from odd.
     */
    public void ex8_writeEvenOdds(){

        Queue<String> evenLines = new ArrayQueue<>(String.class);
        Queue<String> oddLines = new ArrayQueue<>(String.class);
        try (BufferedReader fileReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineCount = 0;
            while ((line = fileReader.readLine()) != null) {
                if(lineCount % 2 == 0)
                    evenLines.add(line);
                else
                    oddLines.add(line);
                lineCount++;
            }
            String wLine;
            while((wLine = evenLines.poll()) != null){
                System.out.println(wLine);
            }
//            Poll returns null if there are no more elements - otherwise it returns a value.
            while((wLine = oddLines.poll()) != null){
                System.out.println(wLine);
            }

        } catch (IOException e) {
            System.out.println("File not found.");
        }
    }
    /**
     * Read the entire input one line at a time and randomly permute the lines before outputting them. To be
     * clear: You should not modify the contents of any line. Instead, the same collection of lines should be
     * printed, but in a random order.
     * - Fisher-Yates Shuffle AKA Knuth Shuffle
     * */
    public void ex9_randomPrinting(){
        List<String> myList = new ArrayList<>();
        try (BufferedReader fileReader = new BufferedReader(new FileReader(filePath))) {

            String line;
            while ((line = fileReader.readLine()) != null) {
                myList.add(line);
            }
            Collections.shuffle(myList);
            myList.forEach(System.out::println);

        } catch (IOException e) {
            System.out.println("File not found.");
        }
        }
    }









