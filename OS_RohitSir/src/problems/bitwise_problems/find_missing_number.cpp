#include <iostream>
#include <bits/stdc++.h>
using namespace std;

int findMissing(vector<int>& nums) {
  int globalXor = nums.size();
  for (int i = 0; i < nums.size(); i++) {
    globalXor ^= (nums[i] ^ i);
  }
  return globalXor;
}
int main() {
  vector<int> nums = {9,6,4,2,3,5,7,0,1};
  cout << (findMissing(nums) == 8 ? "True, ans is 8" : "Wrong Anser");
  return 0;
}
