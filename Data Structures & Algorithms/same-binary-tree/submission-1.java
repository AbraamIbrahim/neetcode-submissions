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
    //GRADING: Time and Space both O(n)
    TreeNode pIt;
    TreeNode qIt;
    boolean same;
    public boolean isSameTree(TreeNode p, TreeNode q) {
        pIt = p;
        qIt = q;
        return checkSame(pIt, qIt);
    }
    //recursive DFS helper
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
