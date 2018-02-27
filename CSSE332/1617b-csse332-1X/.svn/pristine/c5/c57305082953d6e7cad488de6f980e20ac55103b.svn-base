/* Interrupt Handler */
/* Jarrett Alexander, Tucker Osman, Zach Taylor */

#include "./interrupt_handler.h"
#include "./terminal.h"
#include "./floppy_driver.h"
#include "./prog.h"

void handleInterrupt21(int ax, int bx, int cx, int dx) {
	if (ax == 0) {
		printString(bx);
	} else if (ax == 1) {
		readString(bx);
	} else if (ax == 2) {
		readSector(bx, cx);
	} else if (ax == 3) {
		/* call read file with the correct parameters */
		readFile(bx, cx);
	} else if (ax == 4) {
		executeProgram(bx, cx);
	} else if (ax == 5) {
		terminateProgram();
	} else if (ax == 6) {
		writeSector(bx,cx);
	} else if (ax == 7) {
		deleteFile(bx);
	} else if (ax == 8) {
		writeFile(bx, cx, dx);
	} else if (ax == -1) {
		shutdownQemu();
		shutdownBochs();
	} else {
		printString("Not a valid command\r\n\0");
	}
}
