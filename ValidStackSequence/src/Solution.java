// Create a stack and index i and j to point at pushed and poped array
// In a while loop when i or j are not reaching the length of pushed and poped array, do:
// If the top of stack equals popped[j], we pop 1 item from stack and j++
// Else, Check if i has reached the length of pushed array? If so, return false. Else, insert the value from pushed[i] and i++
// Return true after the while loop

import java.util.Deque;
import java.util.LinkedList;

// Time: O(n), Space: O(n)
class Solution {
    public static void main(String[] args) {
        int [] pushed = new int[]{1, 2, 3, 4, 5};
        int [] popped = new int[]{4, 3, 5, 1, 2};
        System.out.print(new Solution().validateStackSequences(pushed, popped));
    }
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Deque<Integer> stack = new LinkedList();
        int i = 0;
        int j = 0;
        while(i != pushed.length || j != popped.length) {
            if(!stack.isEmpty() && stack.peekLast() == popped[j]) {
                stack.pollLast();
                j++;
            }
            else {
                if(i == pushed.length) {
                    return false;
                }
                stack.offerLast(pushed[i]);
                i++;
            }
        }
        return true;
    }
}