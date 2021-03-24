// Given a circular array (the next element of the last element is the first element of the array), print the Next Greater Number for every element. The Next Greater Number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number. If it doesn't exist, output -1 for this number.

import java.util.Deque;
import java.util.LinkedList;

// Stack Solution
// Use a stack to store the index
// Iterating from the end of array. Check from the top of stack, if the value at index on top of stack is greater than current value, update res[i]. Else, keep poping from stack
// If stack is empty, res[i] = -1
// Put the current index to the stack
// Need to iterate again to finish it!
// Time: O(n), Space: O(n)
class Solution {
    public static void main(String[] args) {
        int [] arr = new int[]{1, 2, 4, 3, 1};
        int [] res = new Solution().nextGreaterElements(arr);
        for(int n : res) {
            System.out.print(n + " ");
        }
    }
    public int[] nextGreaterElements(int[] nums) {
        Deque<Integer> stack = new LinkedList();
        int [] res = new int[nums.length];
        for(int i = nums.length * 2 - 1; i >= 0; i--) {
            while(!stack.isEmpty() && nums[stack.peekLast()] <= nums[i % nums.length]) {
                stack.pollLast();
            }
            if(stack.isEmpty()) {
                res[i % nums.length] = -1;
            }
            else {
                res[i % nums.length] = nums[stack.peekLast()];
            }
            stack.offerLast(i % nums.length);
        }

        return res;
    }
}
