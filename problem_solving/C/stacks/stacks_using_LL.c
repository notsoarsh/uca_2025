/**
  *Stack implementation with a LL
*/

#include <stdio.h>
#include <stdlib.h>
#include <limits.h>
#include <assert.h>
#include "stack.h"
#include <stdbool.h>


//represent a node of LL
struct Node{
  int item; //item
  struct Node* next; //next pointer
};


struct Node* head = NULL;
int N = 0;

//isEmpty function
bool isEmpty(){
  return head == NULL;
}

/**
 * Returns the number of elements in the stack
 * @return the number of items in the stack
 */

int size(){
  return N;
}

/**
 * Push function
 * @param item to be pushed in
 */
void push(int item){
  struct Node* oldHead = head;
  struct Node* newNode = (struct Node*) malloc(sizeof(struct Node));
  newNode->item = item;
  newNode->next = oldHead;
  head = newNode;  // Update head to point to the new node
  N++;
}

/**
 * Pop function
 * @returns the top item of the stack
 */
int pop(){
  //check if the stack isEmpty
  if(head == NULL){
    return INT_MIN;
  }

  struct Node* temp = head;
  int item = head->item;
  head = head->next;
  N--;
  free(temp);
  return item;
}

// Tests Stack data structure.
void testStack()
{
  push(3);
  push(5);
  push(7);

  assert(size() == 3);
  assert(isEmpty() == false);
  
  assert(pop() == 7);
  assert(size() == 2);
  assert(isEmpty() == false);

  assert(pop() == 5);
  assert(size() == 1);
  assert(isEmpty() == false);

  assert(pop() == 3);
  assert(size() == 0);
  assert(isEmpty() == true);
}

int main()
{
  testStack();
  printf("All operations are successful!");
  return 0;
}


