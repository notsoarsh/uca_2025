import java.util.*;
import java.lang.*;
import java.io.*;

public class RotatedBS {
  public static void main (String[] args) throws java.lang.Exception  {
    // your code goes here
    int[] arr1 = {4, 5, 6, 7, 0, 1, 2};
    System.out.print(search(arr1, 1));
  }
	
	/**
   * Given a sorted array that is rotated at some pivot (with no duplicate values), find the index of a target value. If the target is not found, return -1.
   *
   * Example: [4, 5, 6, 7, 0, 1, 2], target = 0 -> returns 4.
   *
   * @param nums - Rotated sorted integer array (no duplicates).
   * @param target - The integer to search for.
   * @returns int - Index of target or -1 if not found.
   */
  public static int search(int[] nums, int target) {
    //Approach 
    //Check which half is sorted and in the sorted half try to check if the element exists
    
    int left = 0;
    int right = nums.length - 1;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (nums[mid] == target) {
        return mid;
      } 
      if (nums[left] <= nums[mid]) {
        //means this half is sorted
        if (target >= nums[left] && target < nums[mid]) {
          //target is in this half
          right = mid - 1;
        } else {
          left = mid + 1;
        }
      } else {
        //other half is sorted
        if (target <= nums[right] && target > nums[mid]) {
          //number is present in second
          left = mid + 1;
        } else {
          right = mid - 1;
        }
      }
    }
    return -1;
  }

}
