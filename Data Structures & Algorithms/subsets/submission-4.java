class Solution {
    public List<List<Integer>> solution;
    public int n;
    public int[] numList;
    public List<List<Integer>> subsets(int[] nums) {
        solution = new ArrayList<>();
        n = nums.length;
        numList = nums;
        ArrayList<Integer> start = new ArrayList<>();
        // call recursive helper to fill solution
        backtrack(0, start);
        // return
        return solution;
    }

    // index for tracking how deep we've gone so far
    // path holds nums showing route explored
    public void backtrack(int index, ArrayList<Integer> path){
        // base case
        if(index >= n){
            // add a deep copy, do not insert reference (WILL CHANGE!)
            solution.add(new ArrayList<>(path));
            return;
        }
        // traverse down all possible branches //
        // skip branch
        backtrack(index + 1, path);

        // include branch
        path.add(numList[index]);
        backtrack(index + 1, path);
        
        // backtrack step is completed by removing current element
            // the two branches are called after this one is done
            // bc they're pushed onto the stack
        path.remove(path.size() - 1);
    }
}
