import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class NextGreater {
    /**
     * The next greater element of some element x in an array is the first greater element that is to the right of x in the same array.

    You are given two distinct 0-indexed integer arrays nums1 and nums2, where nums1 is a subset of nums2.

    For each 0 <= i < nums1.length, find the index j such that nums1[i] == nums2[j] and determine the next greater element of nums2[j] in nums2. If there is no next greater element, then the answer for this query is -1.

    Return an array ans of length nums1.length such that ans[i] is the next greater element as described above.
     * Sample Input - nums1 = [4,1,2], nums2 = [1,3,4,2]
     * Sample Output - [-1,3,-1]
     */
    public static void main(String[] args) {
        int[] nums1 = {4,1,2};
        int[] nums2 = {1,3,4,2};
        System.out.println(Arrays.toString(nextGreaterElement(nums1,nums2)));
    }
    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();

        // Process nums2 and store the next greater element in map
        for (int i = nums2.length - 1; i >= 0; i--) {
            int num = nums2[i];

            // Pop smaller or equal elements
            while (!stack.isEmpty() && stack.peek() <= num) {
                stack.pop();
            }

            // Map the current number to the next greater element
            map.put(num, stack.isEmpty() ? -1 : stack.peek());

            // Push current number to stack
            stack.push(num);
        }

        // Build the result for nums1
        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            result[i] = map.get(nums1[i]);
        }

        return result;
    }
}
