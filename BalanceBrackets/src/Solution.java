import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

// A bracket is considered to be any one of the following characters: (, ), {, }, [, or ].
// We consider two brackets to be matching if the first element is an open-bracket, e.g., (, {, or [, and the second bracket is a close-bracket of the same type, e.g., ( and ), [ and ], and { and } are the only pairs of matching brackets.
// Furthermore, a sequence of brackets is said to be balanced if the following conditions are met:
// The sequence is empty, or
// The sequence is composed of two, non-empty, sequences both of which are balanced, or
// The first and last brackets of the sequence are matching, and the portion of the sequence without the first and last elements is balanced.
// You are given a string of brackets. Your task is to determine whether each sequence of brackets is balanced. If a string is balanced, return true, otherwise, return false

public class Solution {
    public static void main(String[] args) {
        String s1 = "{[(])}";
        String s2 = "{{[[(())]]}}";
        System.out.print(new Solution().isBalanced(s2));
    }
    // Use a stack to record all the opening brackets that have passed by
    // Use a hashmap<Opening Bracket, Closing Bracket> to store the matching bracket: <'{', '}'>, <'[', ']'>, <'(', ')'>
    // Iterate the input string, if the current bracket is opening Ex. {, [, and (, put into the stack
    // If the current bracket is a closing one Ex. }, ] and ), check if stack is not empty and it's top has a matching opening bracket use map?
    // if so, pop that from the stack
    // Else, return false
    // Return true after the iteration
    // Edge Case: The length of input string is odd, return false
    // Time: O(n), Space: O(n)

    boolean isBalanced(String s) {
        // Write your code here
        if(s.length() % 2 == 1) {
            return false;
        }
        HashMap<Character, Character> map = new HashMap();
        map.put('{', '}');
        map.put('[', ']');
        map.put('(', ')');

        Deque<Character> stack = new LinkedList();
        for(int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if(cur == '{' || cur == '[' || cur == '(') {
                stack.offerLast(cur);
            }
            else if(cur == '}' || cur == ']' || cur == ')') {
                if(!stack.isEmpty() && map.get(stack.peekLast()) == cur) {
                    stack.pollLast();
                }
                else {
                    return false;
                }
            }
        }
        return true;
    }
}
