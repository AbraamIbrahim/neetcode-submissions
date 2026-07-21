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
    boolean balanced = true;
    public boolean isBalanced(TreeNode root) {
        dfs(root);
        return balanced;
    }
    //recursive post-order helper 
    private int dfs(TreeNode root){
        //sub-tree depth trackers
        int left = 0, right = 0;
        if(root == null){return 0;}
        left = dfs(root.left);
        right = dfs(root.right);
        if(Math.abs(right - left) > 1){balanced = false;}
        return Integer.max(right, left) + 1;
    }
}
