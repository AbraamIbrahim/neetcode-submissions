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
    public int maxDepth(TreeNode root) {
        int depth = depthHelper(root, 0);
        return depth;
    }
    private int depthHelper(TreeNode root, int depth){
        if (root == null){return 0;}
        else if(root.left == null && root.right != null){
            depth = 1 + depthHelper(root.right, depth);
        }
        else if (root.right == null && root.left != null){
            depth = 1 + depthHelper(root.left, depth);
        }
        else{
            //root has 2 children
            depth = 1 + Integer.max(depthHelper(root.right, depth), depthHelper(root.left, depth));
        }
        return depth;
    }
}
