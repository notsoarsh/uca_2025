public class SearchInBiotonicArray {
    public int solve(int[] A, int B) {
        int n = A.length;
        int left = 0, right = n - 1;
        int pivot = -1;

        // Find peak
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if ((mid == 0 || A[mid] > A[mid - 1]) &&
                (mid == n - 1 || A[mid] > A[mid + 1])) {
                pivot = mid;
                break;
            }
            if (A[mid] < A[mid + 1]) { 
                left = mid + 1;  //  in increasing part
            } else {
                right = mid - 1; // in decreasing part
            }
        }

        //Binary search on both sides
        int leftAns = search(A, B, 0, pivot);
        int rightAns = reverse(A, B, pivot + 1, n - 1);

        if (leftAns != -1) return leftAns;
        return rightAns;
    }

    // Normal binary search (increasing order)
    public int search(int[] nums, int target, int left, int right) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] > target) right = mid - 1;
            else left = mid + 1;
        }
        return -1;
    }

    // Reverse binary search (decreasing order)
    public int reverse(int[] nums, int target, int left, int right) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] < target) right = mid - 1; 
            else left = mid + 1;
        }
        return -1;
    }
}
