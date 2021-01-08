// Given a non-negative integer array representing the heights of a list of adjacent bars. Suppose each bar has a width of 1.
// Find the largest rectangular area that can be formed in the histogram.
// The input array is not null or empty

import java.util.Deque;
import java.util.LinkedList;

public class Solution {
    public static void main(String[] args) {
        int [] arr = new int[]{ 2, 1, 3, 3, 4 };
        System.out.print(new Solution().getLargestArea(arr));
    }

    // Iterate the input array, if the current height is less than previous, count the area of previous index
    // Keep counting until the stack is empty or the height at top of stack is less than current height
    // Logic: If the current height is lower than previous, I am confident to count the previous area
    // While loop the array, loop to array.length and set the cur height to 0, so that all previous indices
    // can be count
    // Add the cur index to stack at the end of each for loop
    // Time: O(n), Space: O(n)
    public int getLargestArea(int [] arr) {
        Deque<Integer> stack = new LinkedList();
        int maxArea = 0;
        for(int i = 0; i <= arr.length; i++) {
            int curHeight = i == arr.length ? 0 : arr[i];
            while(!stack.isEmpty() && arr[stack.peekLast()] > curHeight) {
                // Count previous area
                int height = arr[stack.pollLast()];
                int leftBound = stack.isEmpty() ? 0 : stack.peekLast() + 1;
                maxArea = Math.max(maxArea, height * (i - leftBound));
            }
            stack.offerLast(i);
        }
        return maxArea;
    }
}
