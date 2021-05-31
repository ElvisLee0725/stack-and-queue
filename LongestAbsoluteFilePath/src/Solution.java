// If we were to write this representation in code, it will look like this: "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext". Note that the '\n' and '\t' are the new-line and tab characters.
//
//Every file and directory has a unique absolute path in the file system, which is the order of directories that must be opened to reach the file/directory itself, all concatenated by '/'s. Using the above example, the absolute path to file2.ext is "dir/subdir2/subsubdir2/file2.ext". Each directory name consists of letters, digits, and/or spaces. Each file name is of the form name.extension, where name and extension consist of letters, digits, and/or spaces.
//
//Given a string input representing the file system in the explained format, return the length of the longest absolute path to a file in the abstracted file system. If there is no file in the system, return 0.
import java.util.Deque;
import java.util.LinkedList;


// Goal: Find all paths for files. Compare their lengths and return the longest one.
// Stack:
// Use a stack to store each path segment. The number of items in stack is the depth of file path
// Split the input string by "\n" first
// Iterate the array of paths, for each path, split by "\t" again to find how deep it is
// While the stack is deeper, pop it until it the same depth as the cur path
// Calculate the current length by adding the current path's length, plus 1 since '\' + path counts
// Update the final result if current path is a file path
// Return the final max length
class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().lengthLongestPath("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"));
    }
    public int lengthLongestPath(String input) {
        int maxLen = 0;
        int curLen = 0;
        Deque<String> stack = new LinkedList();
        String [] arr = input.split("\n");
        for(String str : arr) {
            String [] a = str.split("\t");
            while(stack.size() != a.length - 1) {
                String tmp = stack.pollLast();
                curLen -= tmp.length() + 1; // '\' count length 1
            }

            curLen += a[a.length-1].length() + 1; // '\' count length 1
            // If current path is file path, calculate the length
            if(a[a.length-1].contains(".")) {
                maxLen = Math.max(maxLen, curLen);
            }
            stack.offerLast(a[a.length-1]);
        }

        // In the end, since the root folder ex. \dir is counted an extra 1 length, we need to subtract it!
        return Math.max(maxLen - 1, 0);
    }
}
