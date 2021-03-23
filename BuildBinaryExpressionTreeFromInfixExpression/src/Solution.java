// A binary expression tree is a kind of binary tree used to represent arithmetic expressions. Each node of a binary expression tree has either zero or two children. Leaf nodes (nodes with 0 children) correspond to operands (numbers), and internal nodes (nodes with 2 children) correspond to the operators '+' (addition), '-' (subtraction), '*' (multiplication), and '/' (division).
//
//For each internal node with operator o, the infix expression that it represents is (A o B), where A is the expression the left subtree represents and B is the expression the right subtree represents.
//
//You are given a string s, an infix expression containing operands, the operators described above, and parentheses '(' and ')'.
//
//Return any valid binary expression tree, which its in-order traversal reproduces s after omitting the parenthesis from it (see examples below).
//
//Please note that order of operations applies in s. That is, expressions in parentheses are evaluated first, and multiplication and division happen before addition and subtraction.
//
//Operands must also appear in the same order in both s and the in-order traversal of the tree.

import java.util.Deque;
import java.util.LinkedList;

public class Solution {
    public static void main(String[] args) {
        Node res = new Solution().expTree("3*4-2*5");
        System.out.print(res.val);
    }
    // Convert the input string to a deque of characters
    // While the deque is not empty, expand from the first and check:
    // Case 1: A number, add it to a operands stack
    // Case 2: '(', call recursion
    // Case 3: ')', calculate all operands and operators and return a node
    // Case 4: An operator. Check if operators stack is not empty and the last operator is not * or / when cur is + or -. If so, do calculate nodes. Then, add current operator into stack
    // Calculate: While there is more than 1 operands in stack, pull 2 out and use operator to combine and put the node back to the operands
    // When deque is empty, do calculate and return the last item in operands
    public Node expTree(String s) {
        Deque<Character> deque = new LinkedList();
        for(Character c : s.toCharArray()) {
            deque.offerLast(c);
        }
        return helper(deque);
    }

    private Node helper(Deque<Character> deque) {
        Deque<Node> operands = new LinkedList();
        Deque<Node> operators = new LinkedList();
        while(!deque.isEmpty()) {
            Character cur = deque.pollFirst();
            if(Character.isDigit(cur)) {
                operands.offerLast(new Node(cur));
            }
            else if(cur == '(') {
                operands.offerLast(helper(deque));
            }
            else if (cur == ')') {
                calculate(operands, operators);
                return operands.pollLast();
            }
            else {
                if(!operators.isEmpty() && !((operators.peekLast().val == '*' || operators.peekLast().val == '/') && (cur == '+' || cur == '-'))) {
                    calculate(operands, operators);
                }
                operators.offerLast(new Node(cur));
            }
        }
        calculate(operands, operators);
        return operands.pollLast();
    }

    private void calculate(Deque<Node> operands, Deque<Node> operators) {
        while(operands.size() > 1) {
            Node cur = operators.pollLast();
            cur.right = operands.pollLast();
            cur.left = operands.pollLast();
            operands.offerLast(cur);
        }
    }
}
