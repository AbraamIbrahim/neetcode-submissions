class Solution {
    public int leastInterval(char[] tasks, int n) {
        // clock functions as CPU cycle number
        // and used to check if a task can be accomplished
        int clock = 0;

        HashMap<Character, Integer> countMap = new HashMap<>();

        // fill countMap
        for (char c : tasks) {countMap.put(c, countMap.getOrDefault(c, 0) + 1);}

        // store all tasks in ArrayList<> for finding
        // smaller one without cooldown
        ArrayList<Task> taskList = new ArrayList<>();

        // max heap (pq) for Task objects
        PriorityQueue<Task> pq = new PriorityQueue<>((a, b) -> Integer.compare(b.count, a.count));

        // fill pq
        for (char c : countMap.keySet()) {
            // time of -1 indicates never accessed
            pq.add(new Task(c, countMap.get(c), -1));
        }

        //queue used to store accessed Tasks in 'time out'
        Queue<Task> queue = new ArrayDeque<>();

        // iterate through tasks
        while (!pq.isEmpty() || !queue.isEmpty()) {
            //check if front of queue can be put back into heap
            Task front = queue.peek();
            if(front != null && front.nextAvail <= clock){pq.add(queue.poll());}
            // always process highest task if not on cooldown
            if(!pq.isEmpty()){
                Task top = pq.poll();
                    //by polling top of PQ every time, 
                    //no need to check if it can be proccessed
                top.count--;
                top.nextAvail = clock + n + 1;
                //throw the task into the 'waiting room' queue iff it needs to be processed again
                if(top.count > 0){queue.add(top);}
                clock++;
            }
            //EROR: DO NOT ONLY PULL FROM QUEUE IF THE MAX HEAP IS EMTPY
                //RESORT TO QUEUE IF NOTHING IN PQ BUT IF SMTH 
                //CAN BE PULLED FROM QUEUE 
                //WITHOUT TIME SKIPPING DO THAT
            //triggers when heap is empty (nothing is not on cooldown)
            else{
                //pull front of queue and add clock idle cycles if needed
                //always readd to heap
                front = queue.poll();
                if(front.nextAvail > clock){clock = front.nextAvail;}
                pq.add(front);
            }
        }
        return clock;
    }

    //with queue + PQ implementation, Task class modified to store nextAvail instead of lastUSE
    public class Task {
        public char letter;
        public int count;
        public int nextAvail;

        public Task(char l, int c, int n) {
            letter = l;
            count = c;
            nextAvail = n;
        }
    }
}