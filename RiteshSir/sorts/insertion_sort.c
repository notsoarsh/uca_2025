#include <stdio.h>

void insertion_sort(int arr[], int n) {
  for (int i = 1; i < n; i++) {
    int key = arr[i];
    int j = i - 1;

    while (j >= 0 && arr[j] > key) {
      arr[j + 1] = arr[j];
      j--;
    }
    arr[j + 1] = key;
  }
}  

int main() {
  int arr[] = {12, 11, 10, 18, 2};
  int n = 5;
  insertion_sort(arr, n);
  int i = 0;
  for (;i < n; i++) {
    printf("%d " , arr[i]);
  }
}

