class Solution {
    public int findKthLargest(int[] nums, int k) {
        //min heap with an invariant: keep it at size k
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        //fill heap
        for(int num : nums){pq.add(num);}
        //remove exeess
        while(pq.size()>k){pq.poll();}
        //return top
        return pq.peek();
    }
}
