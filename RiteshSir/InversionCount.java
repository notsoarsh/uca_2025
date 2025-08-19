import java.util.ArrayList;

public class InversionCounter {

  private static int merge(int[] arr, int low, int mid, int high) {
    ArrayList<Integer> temp = new ArrayList<>();
    int left = low;
    int right = mid + 1;

    // Count of inversions
    int count = 0;

    // Merge process
    while (left <= mid && right <= high) {
      if (arr[left] <= arr[right]) {
        temp.add(arr[left]);
        left++;
      } else {
        temp.add(arr[right]);
        count += (mid - left + 1);
        right++;
      }
    }

    // Add remaining elements from left half
    while (left <= mid) {
      temp.add(arr[left]);
      left++;
    }

    // Add remaining elements from right half
    while (right <= high) {
      temp.add(arr[right]);
      right++;
    }

    // Copy back to original array
    for (int i = low; i <= high; i++) {
      arr[i] = temp.get(i - low);
    }

    return count;
  }

  private static int mergeSort(int[] arr, int low, int high) {
    int count = 0;
    if (low >= high) {
      return count;
    }
    int mid = (low + high) / 2;
    count += mergeSort(arr, low, mid);
    count += mergeSort(arr, mid + 1, high);
    count += merge(arr, low, mid, high);
    return count;
  }

  public static int numberOfInversions(int[] arr, int n) {
    return mergeSort(arr, 0, n - 1);
  }

  public static void main(String[] args) {
    int[] arr = {5, 4, 3, 2, 1};
    int n = arr.length;
    int count = numberOfInversions(arr, n);
    System.out.println("The number of inversions is: " + count);
  }
}
