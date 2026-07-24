/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

class Solution {
    //Time and Space complexities both O(n) --> both optimal!
    public boolean isValidBST(TreeNode root) {
        //FIX: put very small and very large numbers in inital call bc no bounderies exist yet
        return dfs(root, root.val, Integer.MAX_VALUE, Integer.MIN_VALUE);
    }
    //pre-order recursive dfs 
    private boolean dfs(TreeNode root, int parentVal, int high, int low){
        boolean left, right, valid = true;
        //base case
        if(root == null){return true;}
        //ensure invariant according to entire tree path; 
            //this covers the parent invariant!
        if(root.val >= high || root.val <= low){valid = false;}
        //recurse down children
            //MAJOR FIX HERE: send different high and low DOWN TO THE CHILDREN 
            //DEPNDING ON WHICH SUB-TREE YOU ARE GOING DOWN! --> don't update high or low 
            //based on if the PARENT is a left or right child!
        left = dfs(root.left, root.val, root.val, low);
        right = dfs(root.right, root.val, high, root.val);
        //both sub-trees must obey BST invariant for tree to be valid 
        return (left && right && valid);
    }
}