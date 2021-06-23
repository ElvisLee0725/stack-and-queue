// Given two binary search trees root1 and root2.
// Return a list containing all the integers from both trees sorted in ascending order.

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

// Use 2 stacks to implement in-order traverse
// Keep goint to left child of root1 and root2, add nodes into its deque
// Compare the top of deque, add the smaller one into result and move on to its right child
// Keep doing until both deques are empty
// At the end, the arraylist is sorted
// Time: O(N + M), Space: O(N + M)
class Solution {
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> res = new ArrayList();
        Deque<TreeNode> stack1 = new LinkedList();
        Deque<TreeNode> stack2 = new LinkedList();
        while(root1 != null || root2 != null || !stack1.isEmpty() || !stack2.isEmpty()) {
            while(root1 != null) {
                stack1.offerLast(root1);
                root1 = root1.left;
            }

            while(root2 != null) {
                stack2.offerLast(root2);
                root2 = root2.left;
            }

            if(stack2.isEmpty() || !stack1.isEmpty() && stack1.peekLast().val <= stack2.peekLast().val) {
                root1 = stack1.pollLast();
                res.add(root1.val);
                root1 = root1.right;
            }
            else {
                root2 = stack2.pollLast();
                res.add(root2.val);
                root2 = root2.right;
            }
        }
        return res;
    }
}
