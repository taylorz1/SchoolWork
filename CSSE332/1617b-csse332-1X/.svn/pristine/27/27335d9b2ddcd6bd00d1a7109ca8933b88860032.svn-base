/* Kernel */
/* Tucker Osman, Zach Taylor, Jarrett Alexandar */

#include "terminal.h"
#include "floppy_driver.h"
#include "interrupt_handler.h"

char line[512];
char fileBuffer[13312];

int main() {
  initTerminal();
  makeInterrupt21();

  interrupt(0x21, 3, "messag\0", fileBuffer, 0);
  interrupt(0x21, 0, fileBuffer, 0, 0);

  /* interrupt(0x21, 4, "tstprg\0", 0x2000);*/
  /* interrupt(0x21, 4, "tstpr2\0", 0x2000); */
  interrupt(0x21,0,"OS Project (c) 2017 Team 1-X\r\n",0,0);
  interrupt(0x21, 4, "shell\0", 0x2000);

  jmphang();
}
