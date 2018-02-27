/* Program Execution */
/* Tucker Osman, Zach Taylor, Jarrett Alexander */
#include "./kernel.h"
#include "./floppy_driver.h"

char fileBuffer[13312];
int i, j;
extern pcb_struct processes[8];
extern int currentProcess;

void killProcess(int processIndex) {
  setKernelDataSegment();
  processes[processIndex].active = 0;
  processes[0].waiting[1] = 0;
  restoreDataSegment();
}

void executeProgram(char* bx) {
  int i, j;
	char fileBuffer[13312];
	int active, sp;
	readFile(bx, fileBuffer);
	if(fileBuffer[0] == '\0')return;

	for(i = 1; i <= 8; i++) {
		setKernelDataSegment();
		active = processes[mod(i+currentProcess,8)].active;
		sp = processes[mod(i+currentProcess,8)].sp;
    i = mod(i+currentProcess,8);
		restoreDataSegment();
		if(active == 0) {
			for(j = 0; j < 13312; j++) putInMemory((i+2)*0x1000, j, fileBuffer[j]);
			initializeProgram((i+2)*0x1000);
			setKernelDataSegment();
      processes[i].sp = 0xff00;
			processes[i].active = 1;
			restoreDataSegment();
			return;
		}
	}
}

void terminateProgram() {
  int i;
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
  setKernelDataSegment();
  processes[currentProcess].active = 0;
  for (i = 0; i < 8; i++) {
    processes[i].waiting[currentProcess] = 0;
  }
  processes[0].waiting[1] = 0;
  processes[0].active = 1;
  /* sitStill(); */
  while(1) {}
}
