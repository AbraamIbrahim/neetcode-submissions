/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
        //1st pass will copy val and next pointer 
        //2nd pass will copy "random" pointer
            //table used to store nodes to be deep copied second iteration
            //KEY = og LL ref, Value = deep copied ref
        HashMap<Node, Node> table = new HashMap<>();
            //h is head of my deep copied LL
        if(head == null){return null;}
        Node h = new Node(head.val);
        table.put(head, h); 
        Node it = head, current = h;
        //1st loop through entire og LL
        while(it != null){
            table.put(it, current);
            if(it.next != null){current.next = new Node(it.next.val);}
            else{current.next = null; break;}
            it = it.next;
            current = current.next;
        }
        //2nd loop, assign random pointers 
        it = head;
        current = h;
        while(it != null){
            if(it.random != null){current.random = table.get(it.random);}
            else{current.random = null;}
            it = it.next;
            current = current.next;
        }
        return h;
    }
}
