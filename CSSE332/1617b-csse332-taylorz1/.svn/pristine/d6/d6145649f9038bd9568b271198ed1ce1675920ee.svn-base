#include <string.h>
#include <pthread.h>
#include <limits.h>
#include "./threads.h"
#include "./main.h"

int rawData[MAX_BUFFER_CAPACITY];
int sortedData[MAX_BUFFER_CAPACITY];
int dataCount = 0;

static thread_data tdata[NUM_THREADS];
static pthread_t threads[NUM_THREADS];

int main(int argc, char *argv[]) {
    char dataFile[LENGTH];
    char outputFile[LENGTH];

    if (argc < 3) {
        printf("./main <data_file> <output_file>\n");
        return 1;
    }

    strcpy (dataFile, argv[1]);
    strcpy (outputFile, argv[2]);

    readData(dataFile);
    performComputation();
    writeData(outputFile);
    summarizeResults(dataFile, outputFile);

    return 0;
}

void runThread(int threadType) {
    int rc;
    printf("In main: creating thread %d\n", tdata[threadType].tid);
    rc = pthread_create(&(threads[threadType]), NULL, &execute_thread, 
            (void *)&(tdata[threadType]));
    if (rc) {
        printf("ERROR; return code from pthread_create() is %d\n", rc);
        exit(-1);
    }

    rc=pthread_join(threads[threadType], NULL);
    printf("Joined with thread %d\n", tdata[threadType].tid);
    if(rc) {
        printf("ERROR; return code from pthread_join() is %d\n", rc);
        exit(-1);
    }
}

void readData(char *dataFile) {
    tdata[FILE_READER_THREAD].fileName = dataFile;
    tdata[FILE_READER_THREAD].tid = FILE_READER_THREAD;

    runThread(FILE_READER_THREAD);

    dataCount = (int) tdata[FILE_READER_THREAD].results;
    printf("The number of items read from input file is %d.\n", dataCount);
}

void performComputation() {
    int rc;
    tdata[MEAN_CALCULATOR_THREAD].tid = MEAN_CALCULATOR_THREAD;
    tdata[SORTING_THREAD].tid = SORTING_THREAD;
    int threadType = MEAN_CALCULATOR_THREAD;
    while (threadType <= SORTING_THREAD) {
        printf("In main: creating thread %d\n", tdata[threadType].tid);
        rc = pthread_create(&(threads[threadType]), NULL, &execute_thread, 
                (void *)&(tdata[threadType]));
        if (rc) {
            printf("ERROR; return code from pthread_create() is %d\n", rc);
            exit(-1);
        }
        threadType++;
    }

    threadType = MEAN_CALCULATOR_THREAD;
    while (threadType <= SORTING_THREAD) {
        rc=pthread_join(threads[threadType], NULL);
        printf("Joined with thread %d\n", tdata[threadType].tid);
        if (rc) {
            printf("ERROR; return code from pthread_join() is %d\n", rc);
            exit(-1);
        }
        threadType++;
    }
}

void summarizeResults(char* inputFile, char* outputFile) {
    printf("%d items were read from %s\n", dataCount, inputFile);
    printf("The mean of these values is %.2lf\n", 
        tdata[MEAN_CALCULATOR_THREAD].results);
    char *results = dataIsSorted(sortedData);
    printf("Is the data sorted: %s\n", results);
    int count = tdata[FILE_WRITER_THREAD].results;
    printf("Is the data written to %s: %s\n", outputFile, 
        count == dataCount ? "true" : "false");
}

void writeData(char *dataFile) {
    tdata[FILE_WRITER_THREAD].fileName = dataFile;
    tdata[FILE_WRITER_THREAD].tid = FILE_WRITER_THREAD;

    runThread(FILE_WRITER_THREAD);

    int count = (int) tdata[FILE_WRITER_THREAD].results;
    printf("The number of items written to output file is %d.\n", count);
}

char* dataIsSorted(int buffer[]) {
    static char notSorted[] = "false";
    static char sorted[] = "true";
    int max = 0, min = INT_MAX;
    int i;
    max = (max > buffer[0])? max : buffer[0];
    min = (min > buffer[0]) ? buffer[0] : min;
    for (i = 1; i < dataCount; i++) {
        if (buffer[i - 1] > buffer[i]) {
            return notSorted;
        }
        max = (max > buffer[i])? max : buffer[i];
        min = (min > buffer[i]) ? buffer[i] : min;
    } 
    return min < max? sorted : notSorted;
}
