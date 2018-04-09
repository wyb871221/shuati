/*
654. Maximum Binary Tree

Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:

The root is the maximum number in the array.
The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
Construct the maximum tree by the given array and output the root node of this tree.

Example 1:
Input: [3,2,1,6,0,5]
Output: return the tree root node representing the following tree:

      6
    /   \
   3     5
    \    / 
     2  0   
       \
        1
Note:
The size of the given array will be in the range [1,1000].
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// my solution O(nlogn)
class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return construct(nums, 0, nums.length - 1);
    }
    
    private TreeNode construct(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        
        int maxId = start;
        for (int i = start; i <= end; ++i) {
            maxId = nums[maxId] > nums[i] ? maxId : i;
        }
        
        TreeNode root = new TreeNode(nums[maxId]);
        root.left = construct(nums, start, maxId - 1);
        root.right = construct(nums, maxId + 1, end);
        return root;
    }
}

// stack solution, O(n)
class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        Deque<TreeNode> stack = new LinkedList<>();
        for(int i = 0; i < nums.length; i++) {
            TreeNode curr = new TreeNode(nums[i]);
            while(!stack.isEmpty() && stack.peek().val < nums[i]) { // 如果遇见stack中最后点比当前点小的情况，一直出栈，直到栈空或者最后点比当前点大，一致出栈的原因是找到最接近当前点的一个节点，让其成为left
                curr.left = stack.pop();
            }
            if(!stack.isEmpty()) { // 如果stack中最后进的点比当前点大，让当前点作为他的right
                stack.peek().right = curr;
            }
            stack.push(curr);
        }
        
        return stack.isEmpty() ? null : stack.removeLast();
    }
}