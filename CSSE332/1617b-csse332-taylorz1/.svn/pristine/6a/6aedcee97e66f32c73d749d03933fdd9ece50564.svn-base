#include "./sharedMemory.h"
#include "./reader.h"

sharedMemoryStruct *ShmPTR;

int main(int argc, char* argv[]) {
    key_t key = atoi(argv[1]);
    int i, readerid = atoi(argv[2]);
    locateAndAttachSharedMemory(key, &ShmPTR);

    /* entry section */
    for (i = 0; i < NUM_ACCESSES; i++) {
    sleep(1);
    sem_wait(&ShmPTR->mutex);
    ShmPTR -> readers++;
    if (ShmPTR -> readers == 1) {
	sem_wait(&ShmPTR->db);
    }
    sem_post(&ShmPTR->mutex);
    printf("READER \t %d \t about to read. Reader count = %d.\n",
        readerid, ShmPTR->readers);
    /* critical section */
    readDB(readerid);

    /* exit section */
    sem_wait(&ShmPTR->mutex);
    ShmPTR->readers--;
    if (ShmPTR->readers == 0) {
	sem_post(&ShmPTR->db);
    }
    sem_post(&ShmPTR->mutex);
    printf("   READER \t %d \t done reading. Reader count = %d.\n",
        readerid, ShmPTR->readers);
    }
    /* Detach shared memory regioin just before terminating. */
    detachSharedMemory((void *)ShmPTR);
    return 0;
}

void readDB(int who) {
    printf("   READER \t %d \t read %d.\n", who, ShmPTR->data);
}
