#include "./sharedMemory.h"

int createSharedMemoryRegion(key_t key) {
    int ShmID = shmget(key, sizeof(sharedMemoryStruct), IPC_CREAT | 0666);
    if (ShmID < 0) {
        perror("*** shmget error (server) ***\n");
        exit(1);
    }
    return ShmID;
}

sharedMemoryStruct* attachSharedMemoryRegion(int ShmID) {
    sharedMemoryStruct *ShmPTR = (sharedMemoryStruct *) shmat(ShmID, NULL, 0);
    if (ShmPTR == NULL) {
        perror("*** shmat error (server) ***\n");
        exit(1);
    }
    return ShmPTR;
}

void locateAndAttachSharedMemory(key_t key, sharedMemoryStruct **ShmPTR) {
  int ShmID = shmget(key, sizeof(sharedMemoryStruct), 0666);
  if (ShmID < 0) {
    perror("*** shmget error (server) ***\n");
    exit(1);
  }

  *ShmPTR = (sharedMemoryStruct *) shmat(ShmID, NULL, 0);
  if (*ShmPTR == NULL) {
    perror("*** shmat error (client) ***\n");
    exit(1);
  }
}

void detachSharedMemory(void *ShmPTR) {
    shmdt(ShmPTR);
}

void removeSharedMemory(int ShmID) {
    shmctl(ShmID, IPC_RMID, NULL);
}
