CC=gcc
CFLAGS= -c -ansi -Wall -g -D_XOPEN_SOURCE=500
LDFLAGS=  -Wall -g 

PROG = readerswriters

all: $(PROG) writer reader

readerswriters: readerswriters.o sharedMemory.o
	$(CC) $^ $(LDLAGS) -o $@ -pthread
## $@ stores the target of this rule

writer: writer.o sharedMemory.o
	$(CC) $(LDLAGS) $^ -o $@ -pthread
## $^ stores list of all prerequisites/dependencies

reader: reader.o sharedMemory.o
	$(CC) $(LDLAGS) $^ -o $@ -pthread

readerswriters.o: readerswriters.c readerswriters.h sharedMemory.h
	$(CC) $(CFLAGS) $<
## $< stores the first prerequisite/dependency

sharedMemory.o: sharedMemory.c sharedMemory.h
	$(CC) $(CFLAGS) $<

writer.o: writer.c writer.h sharedMemory.h
	$(CC) $(CFLAGS) $<

reader.o: reader.c reader.h sharedMemory.h
	$(CC) $(CFLAGS) $<

clean:
	rm -rf *.o $(PROG) writer reader *~
