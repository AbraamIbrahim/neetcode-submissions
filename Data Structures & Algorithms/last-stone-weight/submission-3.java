class Solution {
    public int lastStoneWeight(int[] stones) {
        //max heap
        PriorityQueue<Integer> heap = new PriorityQueue<>((a,b) -> b-a);
        //fill heap
        for(int stone : stones){heap.add(stone);}
        //cycle through stones
        while(heap.size()>1){
            int x = heap.poll();
            int y = heap.peek();
            System.out.println("The top two stones are " + x + " and "+ y);
            if(x == y){heap.poll();}
            else if(x > y){
                heap.poll();
                heap.add(x-y);
            }
            //y > x
            else{
                //remove y also
                //and readd the difference
                heap.poll();
                heap.add(y-x);
            }
        }
        if(heap.peek() == null){return 0;}
        
        else{return heap.poll();}
    }
}
