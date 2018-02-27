#include "./compute.h"

void bubbleSort(int buffer[], int bufferLength) {
    char sorted = 0;
    int i, temp;
    while (!sorted) {
        sorted = 1;
        for (i = 1; i < bufferLength; i++) {
            if (buffer[i] < buffer[i-1]) {
                sorted = 0;
                temp = buffer[i-1];
                buffer[i-1] = buffer[i];
                buffer[i] = temp;
            }
        }
    }
}

double mean(int buffer[], int bufferLength) {
    int i;
    double total = 0.0;
    for (i = 0; i < bufferLength; i++) {
        total += buffer[i];
    }
    
    return (total / bufferLength);
}

void arrayCopy(int source[], int destination[], int count) {
    int i;
    for (i = 0; i < count; i++) {
        destination[i] = source[i];
    }
}
