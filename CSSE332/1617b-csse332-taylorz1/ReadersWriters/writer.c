#include "./sharedMemory.h"
#include "./writer.h"

sharedMemoryStruct *ShmPTR;

int main(int argc, char* argv[]) {
    key_t key = atoi(argv[1]);
    int i, writerid = atoi(argv[2]);
    locateAndAttachSharedMemory(key, &ShmPTR);

    /* entry section */
    for (i = 0; i < NUM_ACCESSES; i++){
    sleep(1);
    sem_wait(&ShmPTR->db);
    /* critical section */
    writeDB(writerid);
    sem_post(&ShmPTR->db);

    }
    /* exit section */

    /* Detach shared memory regioin just before terminating. */
    detachSharedMemory((void *)ShmPTR);
    return 0;
}

void writeDB(int who) {
    printf("\t WRITER \t %d \t is writing now.\n", who);
    ShmPTR->data +=1;
    printf("\t WRITER \t %d \t wrote %d.\n", who, ShmPTR->data);
}
