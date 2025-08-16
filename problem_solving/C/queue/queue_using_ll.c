#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <assert.h>
#include <limits.h>

/**
 * struct Node that represents each element of the queue as a node
 */
struct Node{
    int item; //item
    struct Node* next; //next pointer
};

/**
 * Global Variables
 */
struct Node* front = NULL;
struct Node* rear = NULL;
int N = 0;

/**
 * isEmpty function - checks if the queue is empty
 * @returns true if the queue is empty
 */
bool isEmpty(){
    return front == NULL;
}

/**
 * Returns the number of elements in the stack
 * @return the number of items in the stack
 */

int size(){
  return N;
}

/**
 * Enqueue Operation
 * Adds a new element to the rear
 */
void enqueue(int item){
    //creting a new node
    struct Node* newNode = (struct Node*)malloc(sizeof(struct Node));
    newNode->item = item;
    newNode->next = NULL;

    if(rear == NULL){
        front = rear = newNode;
    }else{
        rear->next = newNode;
        rear = newNode;
    }
    N++;
}

/**
 * Dequeue Operation
 * @returns the element at front
 */
int dequeue(){
    if(isEmpty()){
        return INT_MIN;
    }
    //creating a temp node
    struct Node* temp = front;
    int item = front->item;
    //un-linking the front node
    front = front->next;
    if(front == NULL){
        rear = NULL;
    }
    free(temp);
    N--;
    return item;
}

/**
 * peek function
 * @returns the front element of the queue
 */
int peek(){
    if(isEmpty()){
        return INT_MIN;
    }
    return front->item;
}

/**
 * Test function that tests the functionality of code during compilation
 */
void testQueue() {
    enqueue(10);
    enqueue(20);
    enqueue(30);

    assert(size() == 3);
    assert(isEmpty() == false);

    assert(dequeue() == 10);
    assert(peek() == 20);
    assert(dequeue() == 20);
    assert(dequeue() == 30);
    assert(isEmpty() == true);
    assert(size() == 0);
}

//main function
int main(){
    testQueue();
    printf("All operations are done successfully!");
    return 0;
    
}
