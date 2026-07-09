/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummyH = new ListNode(-1, head), fast, slow;
        fast = head;
        slow = dummyH;
        //fast and slow pointer will be n spaces apart 
            //once fast reaches end, slow will be one pos 
            //behind node to be removed
        //move fast node until nth node is reached
        while(n > 0){
            fast = fast.next;
            n--;
        }
        //System.out.println("Fast is at " + fast.val + " after the first loop");
        //move both concurrently until fast reaches last node 
        while(fast != null){
            slow = slow.next;
            fast = fast.next;
        }
        //System.out.println("Fast is at " + fast.val + " and slow is at " + slow.val + " after the 2nd loop");
        //rearrange pointers
        slow.next = slow.next.next;
        //return head 
        return dummyH.next;
    }
}
