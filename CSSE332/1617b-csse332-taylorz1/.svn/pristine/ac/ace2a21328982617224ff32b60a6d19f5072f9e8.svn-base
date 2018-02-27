/* Copyright 2016 Rose-Hulman */
#include <stdio.h>
#include <pthread.h>
#include <semaphore.h>
#include <unistd.h>

/**
   In this system, there are 2 typesOfProcesses - producers and
   consumers.  Producers write integers to the shared buffer data.
   Consumers read integers from the shared buffer data.  Everybody has
   to be careful when modifying data and the integer that keeps track
   of how many elements are in data.

   When the producers are being slow (i.e. the buffer is empty) the
   consumers should be blocked a semaphore.  You can simulate this by
   uncommenting the sleep in the producer code.

   When the consumers are being slow (i.e. the buffer is full) the
   producers should be blocked by a semaphore.  You can simulate this
   uncommenting the sleep in the consumer code.

   It does not matter the order the items are consumed (i.e. it does
   not need to be the same as the order they were produced).

   Here's what I would suggest, attempt to figure it out and make a
   first stab at writing the code.  Then, if you get stuck - check out
   the treatmeant in the little book of semaphores (pg 73):

   http://www.greenteapress.com/semaphores/

**/

#define BUFFERSIZE 5
int buffer[BUFFERSIZE];
int lastValidIndex;
sem_t prod;
sem_t cons;

void *producer(void *arg) {
    int i, value = *((int*) arg);  /* you can ignore this linter error */
    for (i = 0; i < 10; i++) {
        sem_wait(&prod);
        buffer[lastValidIndex + 1] = value;
        lastValidIndex++;
        printf("Produced value %d, stored at %d\n", value, lastValidIndex);
        /*sleep(1);*/
        /* feel free to comment this out as part of your
                testing.  I've left it in to make it obvious things
                are not working */
        sem_post(&cons);
        value = value + 1;
    }
    return NULL;
}

void *consumer(void *arg) {
    int value, i;
    /* I consume 10 values, then I stop */
    for (i = 0; i < 10; i++) {
        sem_wait(&cons);
        sleep(1);
        value = buffer[lastValidIndex];
        lastValidIndex--;
        sem_post(&prod);
        printf("Consumed value %d, stored at %d\n", value, lastValidIndex + 1);
    }
    return NULL;
}

int main(int argc, char **argv) {
    pthread_t p1, p2, c1, c2;
    int p1start = 100;
    int p2start = 200;
    sem_init(&cons, 0, 0);
    sem_init(&prod, 0, 5);

    lastValidIndex = -1;  /* initially there is no valid data */
    pthread_create(&p1, NULL, producer, &p1start);
    pthread_create(&p2, NULL, producer, &p2start);
    pthread_create(&c1, NULL, consumer, NULL);
    pthread_create(&c2, NULL, consumer, NULL);

    pthread_join(p1, NULL);
    pthread_join(p2, NULL);
    pthread_join(c1, NULL);
    pthread_join(c2, NULL);

    printf("Everything finished.\n");
    return 0;
}
