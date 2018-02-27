/* Low Level Floppy Functions */
/* Tucker Osman, Zach Taylor, Jarrett Alexandar */

#include "floppy_driver.h"

void readFile(char* fileName, char fileBuffer[13312]) {
	char dirSector[512];
	int i;
	/* Read the directory sector into the sector array and then try to find the 
	name of the file name. If not found, return. If it is found, read the file into
	the file buffer one sector at a time and then return the buffer*/

	readSector(dirSector, 2);

	for (i = 0; i < 16; i++) {
		int count = 0;
		int j;
		for (j = 0; j < 6; j++) {
			if(dirSector[j + i*32] == fileName[j]) {
				count++;
			} else {
				count = 0;
			}
		}
		if (count == 6) {
			/* Found file name, now read it into the fileBuffer */
			int k = 6;
			int l = 0;
			int sector = dirSector[k + i*32];
			while (sector != 0) {
				readSector(fileBuffer + 512*l, sector);
				k++;
				l++;
				sector = dirSector[k + i*32];
			}
			return;
		}
	}
	return;
}

void readSector(char* buf, int sector) {
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
