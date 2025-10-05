import java.util.*;

class Xor_Of_Xor_subsets {

public static void main (String args[]) {
  System.out.println("Expected 5: " + xorOfAllSubsets(new int[]{5}));
  System.out.println("Expected 0: " + xorOfAllSubsets(new int[]{1,2}));
  System.out.println("Expected 0: " + xorOfAllSubsets(new int[]{1,2,3}));


}


public static int xorOfAllSubsets(int[] nums) {
  //Approach is quite simple, if the array contains any more than one element, the ans will be zero as the occurrence of any number will be even times and hence lead to a 0 XOR.
  if (nums.length == 1) return nums[0];
  return 0;
}

}
