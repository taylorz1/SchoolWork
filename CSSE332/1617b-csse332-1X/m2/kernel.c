/* Kernel */
/* Tucker Osman, Zach Taylor, Jarrett Alexandar */

#include "terminal.h"
#include "floppy_driver.h"
#include "interrupt_handler.h"

char line[512];

int main() {
  clearScreen();
  printString("Hello World\r\n\0");

  printString("Enter a line: \0");
  readString(line);
  printString(line);
  printString("\r\n\0");
  readSector(line,30);
  printString(line);

  printString("Using interrupts now...\r\n\r\n\0");
  makeInterrupt21();
  printString("Enter a line: \0");
  interrupt(0x21, 1, line, 0, 0);
  interrupt(0x21, 0, line, 0, 0);
  printString("\r\n\0");
  interrupt(0x21, 2, line, 30, 0);
  interrupt(0x21, 0, line, 0, 0);

  jmphang();
}
