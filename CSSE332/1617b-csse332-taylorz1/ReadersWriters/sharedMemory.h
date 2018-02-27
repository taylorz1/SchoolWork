#ifndef __SHARED_MEMORY_H_
#define __SHARED_MEMORY_H_

#include <stdio.h>
#include <stdlib.h>
#include <semaphore.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <string.h>
#include <unistd.h>
#include <pthread.h>

typedef struct {
  int readers;
  int data;
  sem_t mutex;
  sem_t db;
} sharedMemoryStruct;

#define NUM_ACCESSES 5


int createSharedMemoryRegion(key_t key);

sharedMemoryStruct* attachSharedMemoryRegion(int ShmID);

void locateAndAttachSharedMemory(key_t key, sharedMemoryStruct **ShmPTR);

void detachSharedMemory(void *ShmPTR);

void removeSharedMemory(int ShmID);

#endif
