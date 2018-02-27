#ifndef __THREADS__H_
#define __THREADS__H_

#include <pthread.h>
#include "./file-processing.h"
#include "./compute.h"

#define NUM_THREADS 4

typedef enum {
    FILE_READER_THREAD, 
    MEAN_CALCULATOR_THREAD, 
    SORTING_THREAD,
    FILE_WRITER_THREAD
} thread_type;

typedef struct {
    char *fileName;
    int tid;
    double results;
} thread_data;

extern int rawData[MAX_BUFFER_CAPACITY];
extern int sortedData[MAX_BUFFER_CAPACITY];
extern int dataCount;

void *execute_thread(void *input);

#endif /* __THREADS__H_ */
