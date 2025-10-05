package rest;




import java.util.*;

public class MatchedString {



    /**
     *A matched string is a sequence of {, }, (, ), [, and ] characters that are properly matched. For example,
     * ``{{()[]}}'' is a matched string, but this ``{{()]}'' is not, since the second { is matched with a ].
     * Show how to use a stack so that, given a string of length O(n), you can determine if it is a
     * matched string in O(n) time.
     * https://www.geeksforgeeks.org/dsa/check-for-balanced-parentheses-in-an-expression/
     * */
    public static void matchedString(String s){
        Hashtable<Character, Character> table = new Hashtable<>();
        table.put('}','{');
        table.put(')', '(');
        table.put(']', ')');

        Deque<Character> charStack = new ArrayDeque<>();
//        Opening parenthesis are pushed on the stack
//        Closing parenthesis are compared to the head of teh stack if none exist end
        for (Character c : s.toCharArray()){
            if (table.containsKey(c)){
                if(charStack.peek() != table.get(c)){
                    System.out.println(c + " is unmatched in string!");
                    return;}
                else
                    charStack.pop();
            }
            else
                charStack.push(c);
        }
        if (charStack.isEmpty())
            System.out.println(s + " is well-matched!");
        else
            System.out.println(s + " is not well-matched!");

    }

}
