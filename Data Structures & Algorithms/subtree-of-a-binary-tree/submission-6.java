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
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        return dfs(root, subRoot);
    }
    //recursive helpers
    private boolean dfs(TreeNode main, TreeNode sub){
        boolean left = true, right = true;
        //base cases
        if(main == null && sub == null){return true;}
        if(main == null && sub != null || sub == null && main != null){return false;}
        //potential starting point of substree found --> dig 
        if(main.val == sub.val){
            if (checkSame(main, sub)){return true;}
        }
        //dig in left and right subtrees of main for potential 
        //equivalences to root of sub
        left = dfs(main.left, sub);
        right = dfs(main.right, sub);
    
        return (left || right);
    }
    private boolean checkSame(TreeNode it1, TreeNode it2){
        boolean left = false, right = false;
        if(it1 == null && it2 != null || it2 == null && it1 != null){return false;}
        else if(it1 == null && it2 == null){return true;}
        else if(it1.val != it2.val){return false;}
        else{
            left = checkSame(it1.left, it2.left);
            right = checkSame(it1.right, it2.right);
        }
        return (left && right);
    }
}
