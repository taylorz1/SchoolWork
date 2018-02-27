/* Terminal Functions */
/* Tucker Osman, Zach Taylor, Jarrett Alexandar */

#include "terminal.h"

void initTerminal() {
  interrupt(0x10,3,0,0,0);
  return;
}

void terminalColor(int color) {
  interrupt(0x10,0x0B00,color,0,0);
}

void initGraphics() {
  interrupt(0x10,0x13,0,0,0);
  return;
}

void printString(char* string) {
  int i = 0;
  for(;string[i]!='\0';i++) {
    interrupt(0x10, 0xE*256+string[i], 0, 0, 0);
  }
  return;
}

void readString(char* buf) {
  int bufpos = 0;
  do {
    buf[bufpos] = interrupt(0x16,0,0,0,0);
    if(buf[bufpos]==0x8) {
      if(bufpos==0) continue;
      interrupt(0x10, 0xE*256+buf[bufpos],0,0,0);
      interrupt(0x10, 0xE*256+' ',0,0,0);
      interrupt(0x10, 0xE*256+0x8,0,0,0);
      bufpos--;
    } else {
      interrupt(0x10, 0xE*256+buf[bufpos],0,0,0);
      bufpos++;
    }
  } while(buf[bufpos-1]!=0xD);
  buf[bufpos] = 0xA;
  buf[bufpos+1] = 0;
  printString("\r\n\0");
  return;
}