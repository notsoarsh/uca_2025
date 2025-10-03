#include <stdio.h>
#include <stdlib.h>
#include <string.h>

//max memory size
#define max_size 1000

//structure of memory Block
typedef struct Block {
 int start_add; // starting address of the Block
 int size; //size of the Block
 int is_free; // 1 = if the Block is free, otherwise 0
 int p_id; //if allocated to kisko hai
 struct Block* next;
} Block; 

// head of the linked list
Block* memory_head = NULL;
int allocated_memory = 0;

// function to initialise memory
void init_memory() {
  if (memory_head != NULL) free(memory_head);
  
  //combine full memory into a single free Block
  memory_head = (Block*)malloc(sizeof(Block));
  if (memory_head == NULL) {
    perror("memory couldn't be initialised!");
    exit(EXIT_FAILURE);
  }

  memory_head->start_add = 0;
  memory_head->size = max_size;
  memory_head->is_free = 1;
  memory_head->p_id = 0;
  memory_head->next = NULL;
  allocated_memory = 0;

  printf("memory initialised with %d units of free space: \n", max_size);
}

// function to see how much memory a process has taken as a map representation
void display_memory_alloc() {
  printf("\n------ memory usage ------\n");
  printf("total memory used : %d / %d\n", allocated_memory, max_size);
  
  Block* current = memory_head;
  int free_Blocks = 0;
  int total_free_space = 0;
  
  while (current != NULL) {
    const char* status = current->is_free ? "free" : "allocated";
    printf("| start: %4d | size: %4d | status: %10s | pid: %d |\n", current->start_add, current->size, status, current->p_id);
    
    if (current->is_free) {
      free_Blocks++;
      total_free_space += current->size;
    }
    current = current->next;
  }

  printf("==========================\n");
  printf("total free space : %d units in %d fragments\n", total_free_space, free_Blocks);
  if (free_Blocks > 1 && total_free_space > 0) {
    printf("alert! external fragmentation is present!\n");
  }
}

//first fit allocation
void allocate(int p_id, int size) {
  if (size <= 0) {
    printf("error: size must be positive\n");
    return;
  }

  Block* current = memory_head;
  
  //allocation
  while (current != NULL) {
     //check if splitting is necessary, if free Block is bigger than size req
    if (current->is_free && current->size >= size) {
    
    //check for splitting 
    if (current->size > size) {
      //create a free Block for the rem space
      Block* new_Block_free = (Block*)malloc(sizeof(Block));
      if (new_Block_free == NULL) {
        perror("Memory allocation failed for a new free Block!");
        return;
      }
   
    //free Block details
    new_Block_free->start_add = current->start_add + size;
    new_Block_free->size = current->size - size;
    new_Block_free->is_free = 1;
    new_Block_free->p_id = 0;
    new_Block_free->next = current->next;


    //Allocate to old Block and join at split point
    current->size = size;
    current->next = new_Block_free;
   
    printf("First Fit: Allocated %d units at address %d to Process %d. Block split!\n", size,  current->start_add, p_id);
    
    } else {
      //if size is equal , no split is required
      printf("First Fit : Allocated %d units at adress %d to Process %d. Block Splitting Not Required!", size, current->start_add, p_id);
    } 

    //update the Block
    current->is_free = 0;
    current->p_id = p_id;
    allocated_memory += size;
    return;

    }
    current = current->next;
  }

  //if Block with enough size not found 
  printf("First Fit : Allocation failed for process %d (Size %d). No suitable FREE Block found.\n", p_id, size);
}


//deallocation logic
void free_memory(int p_id) {
  Block* current = memory_head;
  int found = 0;
  
  // Find process id
  while (current != NULL) {
    if (current->is_free == 0 && current->p_id == p_id) {
      //found the Block
      printf("Deallocating %d units from Process %d at address %d.\n", current->size, p_id, current->start_add);
    
    allocated_memory -= current->size;
    current->is_free = 1;
    current->p_id = 0;
    found = 1;

    //Merge Logic: check and merge with the Next Block if its Free
      if (current->next != NULL && current->next->is_free == 1) {
        Block* next_Block = current->next;
        current->size += next_Block->size;
        current->next = next_Block->next;
        free(next_Block);
        printf("Merged successfully with next FREE Block!\n");
      }  
    } 
    current = current->next;
  }
  
  if (!found) {
    printf("Deallocation failed: Process %d not found or already free\n", p_id);
  }
}

//Main
int main() {
  init_memory();
  display_memory_alloc();

  //Allocate initially
  allocate(101, 250);
  allocate(102, 100);
  allocate(103, 350);

  display_memory_alloc();
  
  //deallocate 
  free_memory(102);
  free_memory(101);
  display_memory_alloc();

  allocate(104, 150);
  display_memory_alloc(); //hole 2 splits into 150 and 100 (used and free)
  
  allocate(105, 450);
  display_memory_alloc(); //failed allocation

  free_memory(103);
  free_memory(104);
  display_memory_alloc();
 
  //Memory cleanup
  Block* current_clean = memory_head;
  Block* next_Block;
  while (current_clean != NULL) {
    next_Block = current_clean->next;
    free(current_clean);
    current_clean = next_Block;
  }
  
  return 0; 
} 
  
