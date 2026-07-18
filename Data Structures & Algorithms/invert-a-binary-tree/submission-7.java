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
    public TreeNode invertTree(TreeNode root) {
        //recursively use helper to swap children 
        root = invertHelper(root);
        return root;
    }
    private TreeNode invertHelper(TreeNode root){
        if(root == null){return null;}
        else if(root.left != null && root.right != null){
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;
            root.left = invertHelper(root.left);
            root.right = invertHelper(root.right);
        }
        else if(root.left == null && root.right != null){
            root.left = root.right;
            root.left = invertHelper(root.left);
            root.right = null;
        }
        else if(root.right == null && root.left != null){
            root.right = root.left;
            root.right = invertHelper(root.right);
            root.left = null;
        }
        else{
            //both children null, nothing to do
            return root;
        }
        return root;
    }
}
