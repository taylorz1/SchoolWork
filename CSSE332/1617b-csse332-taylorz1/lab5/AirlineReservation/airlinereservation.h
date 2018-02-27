/* Copyright Zach Taylor 2017 */
/* This is where you define the structs needed to
 * solve this problem.  You can define constants, global
 *  variables, and function signatures here as well.
 *  By <Name>, <Date>
 */

#ifndef __AIRRES_H__
#define __AIRRES_H__

#include <sys/shm.h>
#include <sys/ipc.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <unistd.h>
#include <sys/types.h>
#include <semaphore.h>

#include "./airplane.h"

typedef struct {
    sem_t sem1;
    sem_t sem2;
    sem_t sem3;
    plane planestruct;
} sharedmem;

void executechild(int n, char* name);
#endif /* __AIRRES_H__ */
