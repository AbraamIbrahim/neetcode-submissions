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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //find which treeNode is smaller 
        TreeNode small, large;
        if(p.val < q.val){
            small = p; 
            large = q;
        }
        else{
            large = p;
            small = q;
        }
        return dfs(root, small, large);
    }
    //recursive pre-order traversal helper 
    private TreeNode dfs(TreeNode root, TreeNode small, TreeNode large){
        //debugging 
        System.out.println("The value of the root is " + root.val + ". The vaue of small is " + small.val + " the value of large is " + large.val);
        //base cases 
        if(root == null){return null;}
        //LCA found
        if(small.val <= root.val && root.val <= large.val){return root;}
        //if root is smaller than both nodes, LCA must be in right subtree 
        else if(root.val < small.val){return dfs(root.right, small, large);}
        //otherwise LCA must be in left subtree
        else{return dfs(root.left, small, large);}
    }
}
