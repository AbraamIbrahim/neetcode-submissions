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
    public int goodNodes(TreeNode root) {
        int max = root.val;
        return dfs(root, max);
    }
    //recusrive pre-order traveral helper method 
        //returns number of good nodes each call
    private int dfs(TreeNode root, int max){
        int left = 0, right = 0, good = 0;
        if(root == null){return 0;}
        //check if node is good; update max
        if(root.val >= max){good++;}
        max = Integer.max(max, root.val);
        //recurse down both children
        if(root.left != null){left = dfs(root.left, max);}
        if(root.right != null){right = dfs(root.right, max);}
        //add good nodes from both children
        good += left;
        good += right;
        return good;
    }
}
