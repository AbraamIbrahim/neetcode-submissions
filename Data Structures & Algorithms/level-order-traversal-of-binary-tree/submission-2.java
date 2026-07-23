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
    //GRADING: Time and Space Complexities both O(n), both optimal 
        //TIME IS O(n) DESPITE NESTED LOOPS B.C SUM OF ALL PROCESSED NODES 
        //INSIDE ALL FOR LOOPS FROM ALL WHILE LOOP ITERATIONS STILL EQUALS n!
    public List<List<Integer>> levelOrder(TreeNode root) { 
        //level order traversel is done via BFS, this uses a PQ 
        //where root is proccessed then its children added 
        Queue<TreeNode> q = new ArrayDeque<>();
        List<List<Integer>> nodes = new ArrayList<>();
        //edge case
        if(root == null){return nodes;}
        int levelSize = 0;
        q.add(root);
        //bfs through binary tree while nodes remain
        while(!q.isEmpty()){
            levelSize = q.size();
            List<Integer> level =  new ArrayList<>();
            //process all nodes on the same level together
                //to add to same level list 
            for(int i = 0; i < levelSize; i++){
                TreeNode current = q.poll();
                level.add(current.val);
                //push children onto pq, if the exist 
                    //(TO PROCESS IN NEXT WHILE() LOOP ITERATION)
                if(current.left != null){q.add(current.left);}
                if(current.right != null){q.add(current.right);}
            }
            nodes.add(level);
        }
        return nodes;
    }
}
