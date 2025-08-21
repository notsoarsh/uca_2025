class kthLargest {
    public int findKthLargest(int[] nums, int k) {
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
}
