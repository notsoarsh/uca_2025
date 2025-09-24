/**
   * Given a sorted array of distinct integers and a target value, return the index of target if found, or -1 otherwise.
   *
   * @param nums - Sorted integer array (distinct values). 1 <= nums.length <= 10^5
   * @param target - The integer value to search for.
   * @returns int - Index of target or -1 if not found.
   */
public class SearchInSortedArray {
  public static  int binarySearch(int[] nums, int target) {
    int left = 0;
    int right = nums.length - 1;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (nums[mid] == target) {
        return mid;
      } 
      else if (nums[mid] > target) {
        right = mid - 1;
      } 
      else {
        left = mid + 1;
      } 
     }
    return -1;
  }
  
   /**
   * Main method for testing the SearchInSortedArray class.
   */
  public static void main(String[] args) {
    SearchInSortedArray s = new SearchInSortedArray();
    int[] arr = {1, 2, 3, 4, 5};
    assert s.binarySearch(arr, 1) == 0 : "Test case 1 failed";
    assert s.binarySearch(arr, 3) == 2 : "Test case 2 failed";
    assert s.binarySearch(arr, 5) == 4 : "Test case 3 failed";
    assert s.binarySearch(arr, 6) == -1 : "Test case 4 failed";
  }

}
