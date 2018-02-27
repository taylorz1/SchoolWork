/* Low Level Floppy Functions */
/* Tucker Osman, Zach Taylor, Jarrett Alexandar */

#include "floppy_driver.h"

void readFile(char* name, char fileBuffer[13312]) {
	char dirSector[512];
	char fileName[6];
	int i;
	int j = 0;
	/* Read the directory sector into the sector array and then try to find the
	name of the file name. If not found, return. If it is found, read the file into
	the file buffer one sector at a time and then return the buffer*/

	/* Quickly blank the file buffer so we don't read the last file if we don't find anything */
	fileBuffer[0] = 0;

	/* Normalize the file name so all six characters will match if it exists */
	for(i=0;i<6;i++) fileName[i] = 0; /* Paranoia strikes deep */
	for(i=0;i<6;i++) {
		if(name[i]=='\r'||name[i]=='\n'||name[i]==0) break; /* Filename ends on a NL, CR, or NUL */
		fileName[i] = name[i];
	}

	readSector(dirSector, 2);

	for (i = 0; i < 16; i++) {
		int count = 0;
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

void writeFile(char* name, char filebuffer[13312], int nums) {
	char dirSector[512];
	char mapSector[512];
	char fileName[6];
	int i = 0;
	int j = 0;
	int k = 0;

	for(i=0;i<6;i++) fileName[i] = 0; /* Paranoia strikes deep */
	for(i=0;i<6;i++) {
		if(name[i]=='\r'||name[i]=='\n'||name[i]==0) break; /* Filename ends on a NL, CR, or NUL */
		fileName[i] = name[i];
	}

	readSector(mapSector, 1);
	readSector(dirSector, 2);
	for (i=0;i<16;i++) {
		if (dirSector[32*i]==0) {
			for(j=0;j<6;j++) {
				dirSector[32*i+j] = fileName[j];
			}
			for (j=0;j<512;j++) {
				if (mapSector[j] == 0) {
					mapSector[j] = 0xFF;
					dirSector[32*i+k+6] = j;
					writeSector(filebuffer+k*512,j);
					k++;
					if (k >= nums) break;
				}
			}
			break;
		}
	}
	writeSector(mapSector, 1);
	writeSector(dirSector, 2);
	return;
}

void readSector(char* buf, int sector) {
  interrupt(0x13,2*256+1,buf,div(sector,36)*256+mod(sector,18)+1,mod(div(sector,18),2)*256);
}

void writeSector(char* buf, int sector) {
  interrupt(0x13,3*256+1,buf,div(sector,36)*256+mod(sector,18)+1,mod(div(sector,18),2)*256);
}

void deleteFile(char* name) {
	char mapSector[512];
	char dirSector[512];
	char fileName[6];
	int i, sector;
	int j = 0;

	/* Normalize the file name so all six characters will match if it exists */
	for(i=0;i<6;i++) fileName[i] = 0; /* Paranoia strikes deep */
	for(i=0;i<6;i++) {
		if(name[i]=='\r'||name[i]=='\n'||name[i]==0) break; /* Filename ends on a NL, CR, or NUL */
		fileName[i] = name[i];
	}

	readSector(mapSector, 1);
	readSector(dirSector, 2);

	for (i = 0; i < 16; i++) {
		int count = 0;
		for (j = 0; j < 6; j++) {
			if(dirSector[j + i*32] == fileName[j]) {
				count++;
			} else {
				count = 0;
			}
		}
		if (count == 6) {
			/* Found file fileName, now delete it */
			int k = 6;
			int l = 0;
			dirSector[i*32] = 0; //Set fileName will never match this
			sector = dirSector[k + i*32];
			while (sector != 0) {
				mapSector[sector] = 0;
				k++;
				l++;
				sector = dirSector[k + i*32];
			}
			writeSector(mapSector,1);
			writeSector(dirSector,2);
			return;
		}
	}
	return;
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
