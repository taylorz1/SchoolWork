#ifndef __READERS_WRITERS_H_
#define __READERS_WRITERS_H_

#define MAX_ARRAY_SIZE 32

void makeChild(key_t key, int ID, char* program);

/*You could make it so that when a writer arrives, using a semaphore, 
all processes currently executing in the database finish and that the 
writer is forced to be the next access while all other arriving ones
form a queue that can be allowed to run after the writer finishes executing.
*/


#endif
