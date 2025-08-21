#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <assert.h>

//global variables
int* arr; //pointer to the dynamic array
int capacity = 1;
int N = 0; //no of elements
int front = 0; //index of the front element
int rear = 0; //index of the latest enqueue

/**
 * Resize function
 * @param new_capacity - Takes in the new capacity needed and resizes the array 
 * @returns void
 */
void resize(int new_capacity){
    int* new_arr = (int*)malloc (new_capacity * (sizeof(int)));
    for(int i = 0; i < N; i++){
        new_arr[i] = arr[(front + i) % capacity]; //copy the array
    }
    //free the prev arr
    free(arr);
    arr = new_arr;
    capacity = new_capacity;
    front = 0;
    rear = N;
}
/**
 * Enqueue Operation
 * @param item - The item to be enqueued.
 * @returns void 
*/
void enqueue(int item){
    if(N == capacity){
        resize(capacity * 2);
    }
    arr[rear] = item;
    rear = (rear + 1) % capacity;
    N++;
}
/**
 * Dequeue Operation
 * @returns - the item at the front of queue.
 */
int dequeue(){
    if(N == 0){
        printf("Cant dequeue as the queue is Empty!");
        exit(1);
    }
    int item = arr[front];
    front = (front + 1) % capacity;
    N--;
    //if the elements go less than capacity / 4 , reduce the size of queue to capactity / 2
    if(N > 0 && N <= capacity / 4 && capacity >1){
        resize(capacity*3 / 4);
    }
    return item;
}
/**
 * Peek Function
 * @returns the value of item at the front of queue
 */
int peek(){
    if(N == 0){
        printf("Cant retrieve!, empty queue");
        exit(1);
    }
    return arr[front];
}



/**
 * isEmpty function
 * @returns true if queue is Empty
 */
bool isEmpty(){
    if(N == 0){
        return true;
    }
    return false;
}

/**
 * SIZE FUNCTION
 * @returns the size of the queue
 */
int size(){
    return N;
}

/**
 * Test Function
 * Checks the functionality of the code with assert statements
 */
void test(){
    enqueue(1);
    enqueue(2);
    enqueue(3);
    assert(size() == 3);
    assert(peek() == 1);
    assert(dequeue() == 1);
    assert(dequeue() == 2);
    assert(dequeue() == 3);
    assert(isEmpty());

    // Reuse after resize
    for (int i = 10; i < 20; i++) {
        enqueue(i);
    }
    assert(size() == 10);
    assert(peek() == 10);
    for (int i = 10; i < 20; i++) {
        assert(dequeue() == i);
    }
    assert(isEmpty());
}

/**
 * Main function
 */
int main(){
    arr = (int*)malloc (capacity*sizeof(int));
    test();
    free(arr);
    printf("All test cases passed!\n");
    return 0;
}
