//Code for QuickSort 
#include <stdio.h>

void swap(int a[], int i, int j) {
  if (i == j) return;
  int t = a[i];
  a[i] = a[j];
  a[j] = t;
}

void q_sort(int a[], int L, int R) {
  if (L >= R) return;
  int p = L;
  int i = L + 1;
  
  for (; i <= R; i++) {
    if (a[i] < a[L]) {
      swap(a, i, ++p);
    }
  }
  swap(a, L, p);
  q_sort(a, L, p-1);
  q_sort(a, p+1, R);
}

int main() {
  int a[] = {5,3,7,1,6};
  q_sort(a,0,4);
  int i = 0;
  for (; i < 5; i++) {
    printf("%d", a[i]);
  }
}
