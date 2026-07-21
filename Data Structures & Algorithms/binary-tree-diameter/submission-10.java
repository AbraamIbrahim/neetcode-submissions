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
    //class var!
    int max = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return max;
    }
    //recursive post-order traversal helper, 
        //finds maxDiam and counts depth as it goes
    private int dfs(TreeNode root){
        //sub-tree depth trackers
        int left = 0;
        int right = 0;
        if(root == null){return 0;}
        if(root.left != null){left = dfs(root.left);}
        if(root.right != null){right = dfs(root.right);}
        //store largest diam so far in class var
        max = Integer.max(max, right + left);
        //return the height of the subtree at every iteration 
        //while also counting
        return Integer.max(left, right) + 1;
    }
}
