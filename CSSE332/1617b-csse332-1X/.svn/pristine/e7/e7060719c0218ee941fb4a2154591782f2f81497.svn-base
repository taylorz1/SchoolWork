/* Kernel */
/* Tucker Osman, Zach Taylor, Jarrett Alexandar */

#include "kernel.h"
#include "terminal.h"
#include "floppy_driver.h"
#include "interrupt_handler.h"

int main() {
  initTerminal();

  for(currentProcess=7;currentProcess>=0;currentProcess--) {
    int j;
    for (j = 0; j < 8; j++) {
      processes[currentProcess].waiting[j] = 0;
    }
    processes[currentProcess].active = 0;
    processes[currentProcess].sp = 0xFF00;
  }
  makeTimerInterrupt();
  makeInterrupt21();


  interrupt(0x21, 0, "OS Project (c) 2017 Team 1-X\r\n", 0, 0);
  interrupt(0x21, 4, "shell\0", 0, 0);

  /* jmphang(); */
  /* sitStill(); */
  while(1) {}
}
