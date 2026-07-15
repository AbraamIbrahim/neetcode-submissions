class LRUCache {
    private HashMap<Integer, Node> cacheMap;
    private Node head;
    private Node tail;
    private int currentCap;
    private int maxCap;

    public LRUCache(int capacity) {
        cacheMap = new HashMap<>(); 
        head = new Node(-1, -1, null, null);
        tail = new Node(-1, -1, null, null);
        currentCap = 0; 
        maxCap = capacity;
    }
    
    public int get(int key) {
        //retrieve node
        Node n = cacheMap.get(key);
        int value;
        System.out.println(" Retrieving node with key " + key);
        //dead or non-existent values
        if(n == null || n.value == -1)
        {
            System.out.print(" and a value of -1 ");
            return -1;
        }
        System.out.print(" and a value of " + n.value + " ");
        //swap pointers around n
        n.next.prev = n.prev;
        n.prev.next = n.next;
        //place n at tail
        tail.prev.next = n;
        n.prev = tail.prev;
        n.next = tail;
        tail.prev = n;
        //return 
        return n.value;
    }
    
    public void put(int key, int value) {
        Node n = new Node(key, value, null, null);
        System.out.println("Put operation on key " + key + " with value " + value);
        //delete old node from LL if dupe
        Node old = cacheMap.get(key);
        if(old != null){
            old.prev.next = old.next;
            old.next.prev = old.prev;
            //fix count if dupe was entered 
            currentCap--;
        }
        cacheMap.put(key, n);
        currentCap++;
        if(currentCap == 1){
        //connect to both dummy head and tail
            head.next = n;
            tail.prev = n;
            n.next = tail;
            n.prev = head;
        }
        else{
        //place before dummy tail
            tail.prev.next = n;
            n.prev = tail.prev;
            n.next = tail;
            tail.prev = n;
        }
        if(currentCap > maxCap){
        //O(1) deletion of first real node
            //by moving pointers
            Node dead = head.next;
            System.out.println("Deleting node with key " + dead.key + " and value " + dead.value);
            cacheMap.remove(dead.key);
            head.next.next.prev = head;
            head.next = head.next.next;
            currentCap--;
        }
    }
}

class Node{
    public Node prev;
    public Node next;
    public int key;
    public int value;

    public Node(int key, int value, Node prev, Node next){
        this.prev = prev;
        this.next = next;
        this.key = key;
        this.value = value;
    }
}
