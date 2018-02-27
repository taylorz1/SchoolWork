/* Interrupt Handler */
/* Jarrett Alexander, Tucker Osman, Zach Taylor */

#include "./kernel.h"
#include "./interrupt_handler.h"
#include "./terminal.h"
#include "./floppy_driver.h"
#include "./prog.h"

extern pcb_struct processes[8];
extern int currentProcess;

void handleInterrupt21(int ax, int bx, int cx, int dx) {
	if (ax == 0) {
		printString(bx);
	} else if (ax == 1) {
		readString(bx);
	} else if (ax == 2) {
		readSector(bx, cx);
	} else if (ax == 3) {
		readFile(bx, cx);
	} else if (ax == 4) {
		executeProgram(bx);
	} else if (ax == 5) {
		terminateProgram();
	} else if (ax == 6) {
		writeSector(bx,cx);
	} else if (ax == 7) {
		deleteFile(bx);
	} else if (ax == 8) {
		writeFile(bx, cx, dx);
	} else if (ax == 9) {
		killProcess(bx);
	} else if (ax == 10) {
		setKernelDataSegment();
		processes[0].waiting[1] = 1;
		restoreDataSegment();
		executeProgram(bx);
	} else if (ax == -4) {
		terminalColor(bx);
	} else if (ax == -3) {
		initTerminal();
	} else if (ax == -2) {
		initGraphics();
	} else if (ax == -1) {
		shutdownQemu();
		shutdownBochs();
	} else {
		printString("Not a valid command\r\n\0");
	}
}

void handleTimerInterrupt(int segment, int sp) {
	int i;
	int isWaiting;
	int processCount = 0;
	int j;
	/* decide who's next to run */

	processes[(segment/0x1000)-2].sp = sp;

	for(i=mod(currentProcess + 1, 8);;i = mod(i + 1, 8)) {
		isWaiting = 0;
		for (j= 0; j < 8; j++) {
			if (processes[i].waiting[j] == 1) {
				isWaiting = 1;
				break;
			}
		}
		if(processes[i].active==1 && isWaiting == 0) {
			currentProcess = i;
			returnFromTimer((currentProcess+2)*0x1000,processes[currentProcess].sp);
			return;
		}
		processCount++;
		if (processCount == 8) {
			break;
		}
	}
	returnFromTimer(segment,sp);
}
