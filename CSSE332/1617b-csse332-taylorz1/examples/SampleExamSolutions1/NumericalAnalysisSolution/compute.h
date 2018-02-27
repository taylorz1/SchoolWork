#ifndef _COMPUTE_H_
#define _COMPUTE_H_

/* Uses the bubble sort algorithm to sort the data in the given buffer */
void bubbleSort(int buffer[], int bufferLength);

/* Computes the arithmetic mean of the buffered data */
double mean(int buffer[], int bufferLength);

/* Copies count items from the source buffer to the destination buffer */
void arrayCopy(int source[], int destination[], int count);

#endif /* _COMPUTE_H_ */
