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
    public ArrayList<Integer> keys = new ArrayList<>();
    public int kthSmallest(TreeNode root, int k) {
        dfs(root, k);
        return keys.get(k-1);
    }
    //recursive in-order traversal
    private void dfs(TreeNode root, int k){
        //base case 
        if(root == null){return;}
        //dig left
        dfs(root.left, k);
        //process root
        keys.add(root.val);
        //check if kth smallest int found 
        if(keys.size() >= k){return;}
        //dig right
        dfs(root.right, k);
    }
}
