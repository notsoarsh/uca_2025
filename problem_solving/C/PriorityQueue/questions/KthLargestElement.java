import java.util.*;

public class KthLargestElement {
    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>();
        for(int i : nums){
            maxHeap.offer(-1*i);
        }
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < k ; i++){
            list.add(maxHeap.poll());
        }
        return -list.get((list.size()-1));


    }

    public static void main(String[] args){
        int[] arr = {4,5,12,3,19,15,2};
        int k = 3;
	System.out.println(k + "th Largest Element is : " + findKthLargest(arr,k));
    }
}
