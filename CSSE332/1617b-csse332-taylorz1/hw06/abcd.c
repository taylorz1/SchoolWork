/* Copyright 2016 Rose-Hulman */
#include <stdio.h>
#include <pthread.h>
#include <semaphore.h>
#include <unistd.h>

/**
   In this system, there are 2 threads.  Thread one prints out A and
   C.  Thread two prints out B and D.  Use semaphores to ensure they
   always print A B C D.

   Use semaphores to enforce this constraint.
**/

sem_t alpha;
sem_t beta;

void *threadOne(void *arg) {
    sleep(1);  /* just makes it obvious that it won't work without
                semaphores */
    printf("A\n");
    sem_post(&alpha);
    sem_wait(&beta);
    printf("C\n");
    sem_post(&alpha);
    return NULL;
}

void *threadTwo(void *arg) {
    sem_wait(&alpha);
    printf("B\n");
    sem_post(&beta);
    sem_wait(&alpha);
    printf("D\n");
    return NULL;
}

int main(int argc, char **argv) {
    pthread_t one, two;
    sem_init(&alpha, 0, 0);
    sem_init(&beta, 0, 0);
    pthread_create(&one, NULL, threadOne, NULL);
    pthread_create(&two, NULL, threadTwo, NULL);
    pthread_join(one, NULL);
    pthread_join(two, NULL);

    printf("Everything finished.\n");
    return 0;
}
