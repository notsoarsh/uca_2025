public class RangeSumQuery {
  private int[] nums;
  private int[] st;

  public RangeSumQuery(int[] nums) {
    this.st = new int[nums.length * 4];
    this.nums = nums;
    int idx = 0;
    build(idx, 0, nums.length - 1);
  }


  private void build(int idx, int low, int high) {
    if (low == high) {
      st[idx] = nums[low];
      return;
    }

    int mid = low + (high - low) / 2;
    int start = 2 * idx + 1;
    int end = 2 * idx + 2;
    build(start, low, mid);
    build(end, mid + 1, high);
    st[idx] = st[start] + st[end];
  }

  public int sumQuery(int l, int r) {
    return helper(0, 0, nums.length - 1, l, r);
  }

  private int helper(int idx, int low, int high, int left, int right) {
    if (low > right || high < left) return 0;

    //complete overlap
    if (low >= left && high <= right) {
      return st[idx];
    }

    int mid = low + (high - low) / 2;
    int start = 2 * idx + 1;
    int end = 2 * idx + 2;

    return helper(start, low, mid, left, right) + helper(end, mid + 1, high, left, right);
  }
  
  public void update(int pos, int val) {
    update(0, 0, nums.length - 1, pos, val);
  }

  private void update(int idx, int low, int high, int pos, int val) {
    if (low == high) {
      //exit
      st[idx] = val;
      return;
    }

    int mid = low + (high - low) / 2;
    if (pos <= mid) {
      update(2 * idx + 1, low, mid, pos, val);
    } else {
      update(2 * idx + 2, mid + 1, high, pos, val);
    }

    st[idx] = st[2 * idx + 1] + st[2 * idx + 2];
  }
  public static void main(String[] args) {
    int[] nums = {1, 3, 5};

    RangeSumQuery st = new RangeSumQuery(nums);
    System.out.println(st.sumQuery(0, 1));
    st.update(1, 2);
    System.out.println(st.sumQuery(0, 1));
  }
}
