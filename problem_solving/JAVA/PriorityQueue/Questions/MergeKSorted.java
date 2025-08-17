/* Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class MergeKSorted {
 //This is the brute force approach and has TC - O(NlogN), SC - O(N)  
 public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0){ return null;}
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a,b) -> a.val - b.val);
        for(int i = 0; i < lists.length; i++){
            ListNode curr = lists[i];
            while(curr != null){
                pq.offer(curr);
                curr = curr.next;
            }
        }
        if(pq.isEmpty()) return null;
        ListNode newHead = pq.poll();
        ListNode temp = newHead;
        while(!pq.isEmpty()){
            temp.next = pq.poll();
            temp = temp.next;
            temp.next = null; //cut of the previous link
        }

        return newHead;
    }


   //This is the Optimal Solution that has , TC - O(NlogK), SC - O(N)
   public ListNode mergeKListsOptimal(ListNode[] lists) {
        if(lists.length == 0){ return null;}
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a,b) -> a.val - b.val);
        for(int i = 0; i < lists.length; i++){
            ListNode currHead = lists[i];
            if(currHead != null){
                pq.offer(currHead);
            }
        }
        if(pq.isEmpty()) return null;
        ListNode dummyHead = new ListNode(-1);
        ListNode tail = dummyHead;
        while(!pq.isEmpty()){
            tail.next = pq.poll();
            tail = tail.next;
            if(tail.next != null){
                pq.offer(tail.next);
            }          
        }
        return dummyHead.next;
   }

}
