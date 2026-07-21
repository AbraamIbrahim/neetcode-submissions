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
    public int diameterOfBinaryTree(TreeNode root) {
        return postOrderDiameter(root);
    }
    //recursive helpers
    private int getDepth(TreeNode root){
        if(root == null){return 0;}
        else{
            return Integer.max(getDepth(root.left), getDepth(root.right)) + 1;
        }
    }
    private int postOrderDiameter(TreeNode root){
        int maxDiam = 0;
        int diam = 0;
        int diamRight = 0;
        int diamLeft = 0;
        if(root == null){return 0;}
        else{
            diam = getDepth(root.left) + getDepth(root.right);
            diamLeft = postOrderDiameter(root.left);
            diamRight = postOrderDiameter(root.right);
        }
        maxDiam = Integer.max(diam, maxDiam);
        maxDiam = Integer.max(maxDiam, diamRight);
        maxDiam = Integer.max(maxDiam, diamLeft);
        return maxDiam;
    }
}
