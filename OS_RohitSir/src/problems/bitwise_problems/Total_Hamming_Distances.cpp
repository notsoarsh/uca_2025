#include <iostream>
#include <bits/stdc++.h>
using namespace std;

int totalHammingDistances(vector<int>& nums) {
  //Approach : We will check 0 - 31 bits and count how many numbers have a particular bit set or unset and basically for each bit the hamming distance will no_with_unset_ith_bit x no_with_set_ith_bit
  int total_hamming_distance = 0;
  for (int i = 0; i < 32; i++) {
    int unset_ith = 0;
    int set_ith = 0;
    for (int j = 0; j < nums.size(); j++) {
      if ((nums[j] & (1 << i)) != 0) {
        set_ith++;
      } else {
        unset_ith++;
      }  
    }
    total_hamming_distance += set_ith * unset_ith;
  }
  return total_hamming_distance;
}

int main() {
  vector<int> nums = {4,2,14};
  cout << "Ans: " << totalHammingDistances(nums);
  return 0;
} 
