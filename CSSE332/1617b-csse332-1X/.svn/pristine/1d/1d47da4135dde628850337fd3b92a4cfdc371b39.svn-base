/* Tucker Osman, Zach Taylor, Jarrett Alexandar */

#include "./terminal.h"

int main() {
	char* hello = "Hello, World!";
	clearScreen();
	printString(hello);
	jmphang();
}

void printString(char* s) {
	int i = 0;
	while(s[i]!=0) {
		putInMemory(0xB000,0x8000+(2*i),s[i]);
		putInMemory(0xB000,0x8001+(2*i),0x07);
		i++;
	}
}

void clearScreen() {
	int i = 0;
	for(i=0;i<TERMINAL_WIDTH*TERMINAL_HEIGHT;i++) {
		putInMemory(0xB000,0x8000+(2*i),' ');
		putInMemory(0xB000,0x8001+(2*i),0x07);
	}
}
