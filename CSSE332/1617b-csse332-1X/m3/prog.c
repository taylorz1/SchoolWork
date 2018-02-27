/* Program Execution */
/* Tucker Osman, Zach Taylor, Jarrett Alexander */
#include "./floppy_driver.h"

char fileBuffer[13312];
int i;

void executeProgram(char* bx, int cx) {
  readFile(bx, fileBuffer);
  for(i=0;i<13312;i++) {
    putInMemory(cx,i,fileBuffer[i]);
  }
  launchProgram(cx);
}

void terminateProgram() {
  fileBuffer[0] = '\r';
  fileBuffer[1] = '\n';
  fileBuffer[2] = 'P';
  fileBuffer[3] = 'r';
  fileBuffer[4] = 'o';
  fileBuffer[5] = 'c';
  fileBuffer[6] = 'e';
  fileBuffer[7] = 's';
  fileBuffer[8] = 's';
  fileBuffer[9] = ' ';
  fileBuffer[10] = 'T';
  fileBuffer[11] = 'e';
  fileBuffer[12] = 'r';
  fileBuffer[13] = 'm';
  fileBuffer[14] = 'i';
  fileBuffer[15] = 'n';
  fileBuffer[16] = 'a';
  fileBuffer[17] = 't';
  fileBuffer[18] = 'e';
  fileBuffer[19] = 'd';
  fileBuffer[20] = '\r';
  fileBuffer[21] = '\n';
  fileBuffer[22] = '\0';
  printString(fileBuffer);
  fileBuffer[0] = 's';
  fileBuffer[1] = 'h';
  fileBuffer[2] = 'e';
  fileBuffer[3] = 'l';
  fileBuffer[4] = 'l';
  fileBuffer[5] = '\0';
  executeProgram(fileBuffer, 0x2000);
}
