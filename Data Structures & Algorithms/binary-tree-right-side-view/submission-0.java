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
    public List<Integer> rightSideView(TreeNode root) {
        Queue<TreeNode> q = new ArrayDeque<>();
        List<Integer> visible = new ArrayList<>();
        if(root == null){return visible;}
        q.add(root);
        //dfs level-order search
        while(!q.isEmpty()){
            int levelSize = q.size();
            for(int i = 1; i <= levelSize; i++){
                //process all nodes on the same level tg
                TreeNode current = q.poll();
                if(current.left != null){q.add(current.left);}
                if(current.right != null){q.add(current.right);}
                //add right-most node on each level to final list 
                if(i == levelSize){visible.add(current.val);}
            }
        }
        return visible;
    }
}
