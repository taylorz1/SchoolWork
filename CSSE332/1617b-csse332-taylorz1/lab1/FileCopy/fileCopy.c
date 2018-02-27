/* Implement your solution to the File Copy problem here.
   by Zachary Taylor, 11/30/2016
   CopyRight 2016 Zachary Taylor
*/
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
int ReadLineFromFile(FILE* read, char* buf) {
    char* success;
    success = fgets(buf, 512, read);
    if (success == NULL) {
        return 0;
    }
    return strlen(buf);
}
int WriteLineToFile(FILE* copy, char* buf, int count) {
    fwrite(buf, count, sizeof(char), copy);
}
int main(int argc, char** argv) {
    if (argc < 3) {
        printf("toCopy.txt Copy.txt");
    }
    FILE* input = fopen(argv[1], "r");
    FILE* output = fopen(argv[2], "w+");
    char buf[512];
    int count;
    while (!feof(input)) {
        count = ReadLineFromFile(input, buf);
        WriteLineToFile(output, buf, count);
    }
}
