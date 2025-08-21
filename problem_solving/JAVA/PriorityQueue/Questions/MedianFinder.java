/**
 * Leetcode 295: Find Median from data stream
 * Approach : 2 Heaps 
 * Time Coplexity - O(nlogn) + O(1)
 * Space Complexity - O(n)
*/

class MedianFinder {
    PriorityQueue<Integer> LeftSide; //One MaxHeap to keep track of the left greatest element
    PriorityQueue<Integer> RightSide; //One MinHeap to keep track of the right smallest element

    public MedianFinder() {
        LeftSide = new PriorityQueue<>((a , b) -> b - a );
        RightSide = new PriorityQueue<>();
    }
    
    public void addNum(int num) {
        if(LeftSide.isEmpty()){  //If the leftSide has no element , add it in first
            LeftSide.offer(num);
        }else{
            if(num <= LeftSide.peek()){ //else check  if the number is <= the number from the left side
                LeftSide.offer(num);
            }else{
                RightSide.offer(num);
            }
        }
        //balancing
        if(Math.abs(LeftSide.size() - RightSide.size()) > 1){  //if the difference between two heaps is greater than 2, balance them
            if(LeftSide.size() > RightSide.size()){
                RightSide.offer(LeftSide.poll());  //whichever heap has less elements, add to that heap from other
            }else{
                LeftSide.offer(RightSide.poll());
            }
        }
        
    }
    
    public double findMedian() {
        if((LeftSide.size() + RightSide.size()) % 2 == 0){ //if the elements are even, take top of the heaps and take mean
            return (LeftSide.peek() + RightSide.peek()) / 2.0;
        }else{
            if(LeftSide.size() > RightSide.size()){  //if the elements are odd , return the top element of the heap having more elements
                return LeftSide.peek();
            }else{
                return RightSide.peek();
            }
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
