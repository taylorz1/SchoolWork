CC = gcc
CFLAGS = -Wall -g -pthread -ansi
LDFLAGS =
PROG = threadProblemSolver


all: $(PROG)

$(PROG): realRoots.o equalRoots.o complexRoots.o
	$(CC) $(CFLAGS) realRoots.o equalRoots.o complexRoots.o threadProblemSolver.c threadProblemSolver.h -lm -o $(PROG)


realRoots.o: realRoots.c realRoots.h
	$(CC) $(CFLAGS) realRoots.c -c -o realRoots.o

equalRoots.o: equalRoots.c equalRoots.h
	$(CC) $(CFLAGS) equalRoots.c -c -o equalRoots.o

complexRoots.o: complexRoots.c complexRoots.h
	$(CC) $(CFLAGS) complexRoots.c -c -o complexRoots.o

clean:
	rm -rf threadProblemSolver *.o *.out *~ *.dSYM


