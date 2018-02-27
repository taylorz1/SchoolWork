/* Interrupt Handler */
/* Jarrett Alexander, Tucker Osman, Zach Taylor */

#include "./interrupt_handler.h"
#include "./terminal.h"
#include "./floppy_driver.h"

void handleInterrupt21(int ax, int bx, int cx, int dx) {

	if (ax == 0) {
		printString(bx);
	} else if (ax == 1) {
		readString(bx);
	} else if (ax == 2) {
		readSector(bx, cx);
	} else {
		printString("Not a valid command\r\n\0");
	}
}
