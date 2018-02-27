#include "./file-processing.h"

int readDataFromFile(char *fileName, int* buffer) {
    int count = 0;
    int value;
    char line[100];
    FILE *inputFile = fopen(fileName, "r");

    if (inputFile == NULL) {
        fprintf(stderr, "Unable to open the file %s\n", fileName);
        exit(2);
    }

    while (fgets(line, 100, inputFile) && (count + 1 < MAX_BUFFER_CAPACITY)) {
        if (1 == sscanf(line, "%d", &value)) {
            buffer[count] = value;
            count++;
        }
    }

    fclose(inputFile);
    return count;
}

int writeDataToFile(char *fileName, int* buffer, int numberOfItems) {
    int count;
    FILE *outFile = fopen(fileName, "w");
    printf("The name of the output file is %s\n", fileName);
    if (outFile == NULL) {
        fprintf(stderr, "Unable to open the file %s\n", fileName);
        exit(2);
    }

    for (count = 0; count < numberOfItems; count++) {
        fprintf(outFile, "%d\n", buffer[count]);
    }

    fclose(outFile);
    return count;
}
