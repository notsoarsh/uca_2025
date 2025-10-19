#include <stdio.h>
#include <stdlib.h>
#include <string.h>

//Macro that defines the way to return the address of an element, return as void*
#define PTR_AT(base, i, size) ((char*)(base) + ((i) * (size)))

//function that will compare two elements
int compare(const void* a, const void* b) {
  return (*(int*)a - *(int*)b);
}

//custom swap
void swap(void* a, void* b, size_t size) {
  char temp[size];
  memcpy(temp, a, size);
  memcpy(a, b, size);
  memcpy(b, temp, size);
}

void q_sort(void* arr, int left, int right, size_t ele, int (*compare)(const void *, const void *)) {
  if (left >= right) return;

  void *pivot = PTR_AT(arr, (left + right)/2, ele);
  int i = left, j = right;
  while (i <= j) {
    //Move till you find ele bigger than pivot and j less than pivot
    while (compare(PTR_AT(arr, i, ele), pivot) < 0) i++; 
    while (compare(PTR_AT(arr, j, ele), pivot) > 0) j--;
    
    //Swap the elements as they are on wrong side of pivot
    if (i <= j) {
      swap(PTR_AT(arr, i, ele), PTR_AT(arr, j, ele), ele);
    i++;
    j--;
    }
  }
  
  //Recursive calls
  q_sort(arr, left, j, ele, compare);
  q_sort(arr, i, right, ele, compare);
}

void quickSort(void* arr, size_t n, size_t ele, int(*compare)(const void*, const void*)) {
  q_sort(arr, 0, n - 1, ele, compare); 
}

int main() {
  int arr[] = {3,4,6,2,1,8,10};
  int size = 7;
  quickSort(arr, size, sizeof(int), compare);
  for (int i = 0; i < 7; i++) {
    printf("%d ", arr[i]);
  }
  return 0;
}
