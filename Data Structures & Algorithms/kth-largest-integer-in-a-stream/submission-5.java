class KthLargest {
    //min heap
    public ArrayList<Integer> heap;
    public ArrayList<Integer> sorted;
    int k;

    public KthLargest(int k, int[] nums) {
        heap = new ArrayList<>();
        this.k = k;
        //fill heap 
        //MAJOR ISSUE WITH HEAP FILLING:
            //FILLIGN FIRST K NUMS DOES NOT GAURENTEE I FILLED TOP K LARGEST NUMS!
            //FILL ALL NUMS INSIDE HEAP THEN EXTRACT UNTIL HEAP CORRECT SIZE
        for(int i = 0; i < nums.length; i++){heap.add(nums[i]);}
        heapify();
        while(heap.size() > k){extract();}
    }
    
    public int add(int val) {
        heap.add(val);
        siftUp(heap.size()-1);
        if(heap.size() > k){extract();}
        return heap.get(0);
    }

    //helper
    private void heapify(){
        //find first non-leaf node 
        int start = (heap.size()/2)-1;
        //sift down from right to left 
        for(int i  = start; i >= 0; i--){siftDown(i);}
    }

    //helper
    private void siftUp(int index){
        if(heap == null){return;}
        int nodeVal = heap.get(index);

        //force stop if root reached
        while(index > 0){
            //keep swapping with parent until node is in legal pos
            int parentIndex = (index - 1)/2;
            int parentVal = heap.get(parentIndex);
            if(parentVal <= nodeVal){return;}
            else{
                //swap values
                heap.set(parentIndex, nodeVal);
                heap.set(index, parentVal);
                index = parentIndex;
            }
        }
    }

    //helper
    private void siftDown(int index){
        if(heap == null){return;}
        //force stop if bottom level reached
            //i.e no (left) child
        while(2 * index + 1 < heap.size()){
            int left = 2*index+1;
            int right = 2*index+2;
            //assume left is smaller
            int smallest = left;
            //confirm largest child
            if(right < heap.size() && heap.get(smallest) > heap.get(right)){smallest = right;}
            //check if parent already in right pos
            if(heap.get(index) <= heap.get(smallest)){return;}
            //swap with smaller child
            int tempVal = heap.get(index);
            heap.set(index, heap.get(smallest));
            heap.set(smallest, tempVal);
            index = smallest;
        }
    }

    //new extract helper needed
    //for if heap size exceeds k
    private void extract(){
        //swap root with rightmost node, then delete old root
            //and siftDown() new root
        int tempVal = heap.get(heap.size()-1);
        heap.set(heap.size()-1,heap.get(0));
        heap.set(0, tempVal);
        heap.remove(heap.size()-1);
        siftDown(0);
    }
}
