/*
 * Header file for the file functions used to read data from a file or write 
 * data to a file.
 */

#ifndef FILE_PROCESSING_H_
#define FILE_PROCESSING_H_

#include <stdio.h>
#include <stdlib.h>

#define MAX_BUFFER_CAPACITY 1000000

 /* Reads the data from the file with the given name, and store in the given 
  * buffer. Returns the number of items read.
  */
int readDataFromFile(char *fileName, int* buffer);

 /* Writes the number of items stored in the given buffer to the file with 
  * the given name and.
  */
int writeDataToFile(char *fileName, int* buffer, int numberOfItems);

#endif /* FILE_PROCESSING_H_ */
