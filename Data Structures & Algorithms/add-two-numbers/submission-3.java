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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head, prev, current;
        head = new ListNode(-1);
        current = head;
        boolean carryOne = false, firstNode = true, oneMore = false;
        int sum = 0, n = 0;
        //loop through both LLs simultaneously 
        while(l1 != null || l2 != null){
        //if-elif chain to add placeholder zeros if either iterator 
            //is null 
            if(l1 != null && l2 != null){sum = l1.val + l2.val;}
            else if(l1 != null && l2 == null){sum = l1.val;}
            else{sum = l2.val;}
            if(carryOne){sum++;}
            if(sum > 9){
                carryOne = true;
                prev = current;
                current = new ListNode(sum - ((sum/10)*10));
                prev.next = current;
                if(firstNode){
                    head = current;
                    firstNode = false;
                }
            }
            else{
                carryOne = false;
                prev = current;
                current = new ListNode(sum);
                prev.next = current;
                if(firstNode){
                    head = current;
                    firstNode = false;
                }
            }
            //move iterators of both LLs
            if(l1 != null && l2 != null){
                l1 = l1.next;
                l2 = l2.next;
            }
            else if(l1 != null && l2 == null){l1 = l1.next;}
            else{l2 = l2.next;}

        }
        //take care of extra last node outside loop 
        if(carryOne){
            prev = current;
            current = new ListNode(1);
            prev.next = current;
        }
        return head;
    }
}
