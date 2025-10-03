#include <stdio.h>
#include <stdlib.h>

#define MAX_MEMORY_SIZE 1000

typedef struct Block {
  int start_add;
  int size;
  int is_free;
  int p_id;
  struct Block* next;
} Block;

//Two pointers for two linked list(allocated and free)
Block* allocated_head = NULL;
Block* free_head = NULL;
int allocated_memory = 0;

void init_memory() {
  //free the previous memory for safety
  if (allocated_head != NULL) {
    /*Cleanup*/
  }
  if (free_head != NULL) {
    free(free_head);
  }

  //Combine the memory into a single block
  free_head = (Block*)malloc(sizeof(Block));
  if (free_head == NULL) {
    perror("Initial memory allocation failed!\n");
    exit(EXIT_FAILURE);
  }

  free_head->start_add = 0;
  free_head->size = MAX_MEMORY_SIZE;
  free_head->is_free = 1;
  free_head->p_id = 0;
  free_head->next = NULL;

  allocated_head = NULL;
  allocated_memory = 0;
  
  printf("Memory initialised with %d free space units!\n",MAX_MEMORY_SIZE);
}

//addition to allocated list
void add_to_allocated(Block* new_block) {
  new_block->next = allocated_head;
  allocated_head = new_block;
}

//removal from allocated list
void remove_from_allocated(int process_id) {
  Block* current = allocated_head;
  Block* prev = NULL;
  
  while (current != NULL && current->p_id != process_id) {
    prev = current;
    current = current->next;
  }

  if (current == NULL) return; //no such process block found
  
  if (prev == NULL) {
    allocated_head = current->next;
  } else {
    prev->next = current->next;
  }
}

//allocation function
void allocate(int process_id, int size) {
  if (size <= 0) { 
    perror("Size has to be non-zero or non-negative!!\n");
    return;
  }

  Block* current = free_head;
  Block* prev = NULL;
 
  //search first free block in FREE list
  while (current != NULL) {
    if (current->size >= size) {
      //Found a valid block so remove from this list
      if (prev == NULL) {
        free_head = current->next;
      } else {
        prev->next = current->next;
      }

      //Check for splitting
      if (current->size > size) {
        Block* new_free_block = (Block*)malloc(sizeof(Block)) ;
        if (new_free_block == NULL) {
          perror("Failed to split the block!\n");
          return;
        }
      
        //set details for the block and add to FREE
        new_free_block->start_add = current->start_add + size;
        new_free_block->size = current->size - size;
        new_free_block->is_free = 1;
        new_free_block->p_id = 0;
        new_free_block->next = free_head; //insert at head
        free_head = new_free_block;

        current->size = size;
        printf("Allocated %d units to Process %d at address %d. Block was split and new free block added\n",size, process_id, current->start_add);
      } else {
          printf("Allocated %d units to Process %d at address %d. Block split wasnt required..\n", size, process_id, current->start_add);
      }

    //update the current block and add to allocated list
    current->is_free = 0;
    current->p_id = process_id;
    current->next = NULL;
    add_to_allocated(current);
    allocated_memory += size;
    return;
    }
    prev = current;
    current = current->next;
  }
  printf("Allocation failed for process %d, no suitable blocks found!\n", process_id);
}

//method to free allocated 
void free_memory(int process_id) {
  //find the block in allocated and remove
  Block* block_to_free = allocated_head;
  Block* prev = NULL;
  
  while (block_to_free != NULL && block_to_free->p_id != process_id) {
    prev = block_to_free;
    block_to_free = block_to_free->next;
  }
  
  if (block_to_free == NULL) {
    printf("No such process found!!\n");
    return;
  }     

  //allocated list removal
  if (prev == NULL) {
    allocated_head = block_to_free->next;
  } else {
    prev -> next = block_to_free->next;
  }

  printf("Deallocating %d units of memory from process %d at address %d\n",block_to_free->size, process_id, block_to_free->start_add);
  allocated_memory -= block_to_free->size;

  //set block to free state
  block_to_free->is_free = 1;
  block_to_free->p_id = 0;
  

  //insert to FREE list
  block_to_free->next = free_head;
  free_head = block_to_free;
  printf("Block added to FREE list!\n");
}

//method for defrag
void defrag() {
  printf("\n\n**** DEFRAGGING *****\n");
  if (allocated_head == NULL) {
    printf("Memory is already empty ! No need to DEFRAG.\n");
    return;
  }
  
  Block* current = allocated_head;
  int current_physical_add = 0;
  int total_free_space = MAX_MEMORY_SIZE - allocated_memory;
  
  //Move allocated blocks to start of physical memory 
  while (current != NULL) {
    printf("  Moving process from %d from %d to %d\n", current->p_id, current->start_add, current_physical_add);
    current->start_add = current_physical_add;
    current_physical_add += current->size;
    current = current->next;
  }

  //delete the old FREE
  current = free_head;
  Block* next_block;
  while (current != NULL) {
    next_block = current->next;
    free(current);
    current = next_block;
  }
  free_head = NULL; //empty FREE

  //Create a bigger FREE
  if (total_free_space > 0) {
    Block* new_free_block = (Block*)malloc(sizeof(Block));
    if (new_free_block == NULL) {
      perror("Final FREE block coudlnt be made!\n");
      return;
    }
    new_free_block->start_add = current_physical_add;
    new_free_block->size = total_free_space;
    new_free_block->is_free = 1;
    new_free_block->p_id = 0;
    new_free_block->next = NULL;
  
    free_head = new_free_block;
  }

  printf("Congratulations DEFRAGMENTATION COMPLETE!.. Free space starts from %d and size %d\n",current_physical_add, total_free_space);
}

int main() {
  init_memory();

  //Fragmentation made
  allocate(101, 200);
  allocate(102, 100);
  allocate(103, 300);

  free_memory(102);
  free_memory(101);

  defrag();

  allocate(104, 550);

  return 0;
}
 
  
