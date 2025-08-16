#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <assert.h>

struct Node{
    int item;
    struct Node* next;
};

struct Node* front = NULL;
struct Node* rear = NULL;
int N = 0;

bool isEmpty(){
    return front == NULL;
}

int size(){
    return N;
}

void enqueue(int item){
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

int dequeue(){
    if(isEmpty()){
        return -1;
    }
    struct Node* temp = front;
    int item = front->item;
    front = front->next;
    if(front == NULL){
        rear = NULL;
    }
    free(temp);
    N--;
    return item;
} 

