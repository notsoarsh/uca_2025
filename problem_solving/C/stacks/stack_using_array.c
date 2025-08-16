#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <assert.h>

//global variables
int* arr; //pointer to a dynamic array
int capacity = 1;
int N = 0;

//resize function
void resize(int size){
	int* new_arr = (int *) malloc(size * sizeof(int));
	for(int i = 0; i < N; i++) {
		new_arr[i] = arr[i];
	}
	free(arr);
	arr = new_arr;
	capacity = size;
}

//push function
void push(int item){
	if(N == capacity){
	   capacity *= 2;
           resize(capacity);
	}
	arr[N++] = item;
}

//pop function
int pop(){
	if(N <=0){
	  printf("No element to pop\n");
	  exit(0);
	}
	int item = arr[--N];
	if(N < capacity / 4 && capacity > 1){
	  resize(capacity / 2);
	}
	return item;
}

bool isEmpty(){
	return N == 0;
}

int size(){
	return N;
}

//test method
void test(){
        push(1);
        push(10);
        assert(size() == 2);
	assert(pop() == 10);
	assert(pop() == 1);
	assert(isEmpty() == true);
	
	push(5); push(6); push(7); push(8); push(9); push(10);
	assert(size() == 6);
}

//main
int main(){
	arr = (int *) malloc(capacity * sizeof(int));
	test();
	free(arr);
	printf("All test cases passed!\n");
	return 0;
}
