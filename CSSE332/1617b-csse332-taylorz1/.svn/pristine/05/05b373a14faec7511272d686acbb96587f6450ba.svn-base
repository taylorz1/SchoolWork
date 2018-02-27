CC = gcc
CFLAGS = -Wall -ansi -pthread -g -Wcpp
all: airlinereservation

airlinereservation: airlinereservation.o airplane.o
	$(CC) airplane.o airlinereservation.o -o airlinereservation $(CFLAGS)

airlinereservation.o: airlinereservation.c airlinereservation.h
	$(CC) airlinereservation.c -c -o airlinereservation.o $(CFLAGS)

airplane.o: airplane.c airplane.h
	$(CC) airplane.c -c -o airplane.o $(CFLAGS)

clean:
	rm -f airlinereservation *.o output.txt
