#include "./threads.h"

void *execute_thread(void *input) {
    thread_data *data = (thread_data *)input;

    if (data->tid == FILE_READER_THREAD) {
        data->results =  readDataFromFile(data->fileName, rawData);
    }

    if (data->tid == MEAN_CALCULATOR_THREAD) {
        data->results =  mean(rawData, dataCount);
    }

    if (data->tid == SORTING_THREAD) {
        arrayCopy(rawData, sortedData, dataCount);
        bubbleSort(sortedData, dataCount);
    }

    if (data->tid == FILE_WRITER_THREAD) {
        data->results =  writeDataToFile(data->fileName, sortedData, dataCount);
    }

    printf("Thread %d is done executing.\n", data->tid);
    pthread_exit(NULL);
}
