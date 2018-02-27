## Variables:
## The name of the C compiler.  The default is gcc
CC = gcc

## Name the executable program
PROG = main

## Compiler and Linker flags
CFLAGS = -ansi -Wall -g 

LDFLAGS = 

## Build the program from the object files
$(PROG): main.o processScheduler.o queue.o
	$(CC) $(LDFLAGS) main.o processScheduler.o queue.o -o $(PROG)

main.o: main.c processScheduler.h queue.h
	$(CC) -c main.c  $(CFLAGS) -o main.o

processScheduler.o: processScheduler.c processScheduler.h queue.h
	$(CC) -c processScheduler.c  $(CFLAGS) -o processScheduler.o

queue.o: queue.c queue.h
	$(CC) -c queue.c  $(CFLAGS) -o queue.o

## Remove all the compilation and debugging and vestigial files
clean: 
	rm -f core $(PROG) *.o *~
