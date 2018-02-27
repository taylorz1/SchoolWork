CC = gcc
CFLAGS = -Wall -c -g
LDFLAGS = -Wall -g

all: client server pipes_simple shared_simple factoring

pipes_simple: pipes_simple.c
	$(CC) -o pipes_simple pipes_simple.c $(LDFLAGS)

shared_simple: shared_simple.c
	$(CC) -o shared_simple shared_simple.c $(LDFLAGS)

factoring: factoring.c
	$(CC) -o factoring factoring.c $(LDFLAGS)

server: server.o
	$(CC) -o server server.o $(LDFLAGS)

server.o: server.c
	$(CC) -c server.c  $(CFLAGS)

client: client.o
	$(CC) -o client client.o $(LDFLAGS)

client.o: client.c
	$(CC) -c client.c $(CFLAGS)

clean:
	rm -rf client client.o server server.o simple *~
