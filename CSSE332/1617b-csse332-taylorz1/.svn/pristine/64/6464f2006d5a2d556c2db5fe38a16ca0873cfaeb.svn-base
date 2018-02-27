CC = gcc

CFLAGS = -Wall -c -g -ansi -pthread

## A list of options to pass to the linker. 
LDFLAGS = -Wall -g -pthread

## Name the executable program, list header files and source files
PROG = main
HDRS = $(wildcard *.h)
SRCS = $(wildcard *.c)

## Give the object files the same names as the source files.
## Only change the extension from .c to .o.
OBJS = $(SRCS:.c=.o)

## Build the program from the object files
$(PROG): $(OBJS)
	$(CC) $(LDFLAGS) -o $(PROG) $(OBJS) 

## Rules for the source files.  They compile each X.c to make X.o
%.o: %.c %.h $(HDRS)
	$(CC) $(CFLAGS) -c -o $@ $<

## Remove all the compilation and debugging files
clean: 
	rm -f core $(PROG) $(OBJS) *~
