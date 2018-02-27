#ifndef _MAIN_H_
#define _MAIN_H_

#define LENGTH 30

void readData(char *dataFile);
void writeData(char *dataFile);
char* dataIsSorted(int buffer[]);
void performComputation();
void summarizeResults(char* inputFile, char* outputFile);

#endif /* _MAIN_H_ */
