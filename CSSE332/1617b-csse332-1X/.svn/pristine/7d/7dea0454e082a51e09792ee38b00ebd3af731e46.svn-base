/* Low Level Floppy Functions */
/* Tucker Osman, Zach Taylor, Jarrett Alexandar */

#include "floppy_driver.h"

void readSector(char* buf, int sector){
  interrupt(0x13,2*256+1,buf,div(sector,36)*256+mod(sector,18)+1,mod(div(sector,18),2)*256);
}

int mod(int x, int y) {
  while (x>=y) x=x-y;
  return x;
}

int div(int x, int y) {
  int quotient = 0;
  while ((quotient + 1) * y <= x) quotient  = quotient  + 1;
  return quotient;
}
