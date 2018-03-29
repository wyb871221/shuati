/*
617. Merge Two Binary Trees

Given two binary trees and imagine that when you put one of them to cover the other, some nodes of the two trees are overlapped while the others are not.

You need to merge them into a new binary tree. The merge rule is that if two nodes overlap, then sum node values up as the new value of the merged node. Otherwise, the NOT null node will be used as the node of new tree.

Example 1:
Input: 
	Tree 1                     Tree 2                  
          1                         2                             
         / \                       / \                            
        3   2                     1   3                        
       /                           \   \                      
      5                             4   7                  
Output: 
Merged tree:
	     3
	    / \
	   4   5
	  / \   \ 
	 5   4   7
Note: The merging process must start from the root nodes of both trees.
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
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// my solution
class Solution {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        return merge(t1, t2);
    }
    
    private TreeNode merge(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return null;
        }
        
        TreeNode left = null;
        TreeNode right = null;
        int val = 0;
        if (t1 == null) {
            val = t2.val;
            left = merge(null, t2.left);
            right = merge(null, t2.right);
        } else if (t2 == null) {
            val = t1.val;
            left = merge(null, t1.left);
            right = merge(null, t1.right);
        } else {
            val = t1.val + t2.val;
            left = merge(t1.left, t2.left);
            right = merge(t1.right, t2.right);
        }
        TreeNode root = new TreeNode(val);
        root.left = left;
        root.right = right;
        return root;
    }
}

// concise solution
class Solution {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
        	return t2;
        }

        if (t2 == null) {
        	return t1;
        }

        t1.val += t2.val;
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTress(t1.right, t2.right);
        return t1;
    }
}
