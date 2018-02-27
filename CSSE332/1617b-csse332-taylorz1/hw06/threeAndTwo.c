/* Copyright 2016 Rose-Hulman */
#include <stdio.h>
#include <pthread.h>
#include <semaphore.h>
#include <unistd.h>
sem_t a;
sem_t b;

/**
   In this system, there are 2 typesOfProcesses - A and B.  A and B
   process can safely run at the same time - they don't affect each
   other.  Only 2 As can safely run at the same time.  Only 3 Bs can
   safely run at the same time.

   Use semaphores to enforce this constraint.
**/

void *typeA(void *arg) {
    sem_wait(&a);
    printf("Starting A\n");
    sleep(1);
    printf("A finished.\n");
    sem_post(&a);
    return NULL;
}

void *typeB(void *arg) {
    sem_wait(&b);
    printf("Starting B\n");
    sleep(1);
    printf("B finished.\n");
    sem_post(&b);
    return NULL;
}

int main(int argc, char **argv) {
    pthread_t tid[10];
    pthread_attr_t attr;
    int i;
    sem_init(&a, 0, 2);
    sem_init(&b, 0, 3);

    for (i = 0; i < 5; i++) {
        pthread_create(&tid[i], NULL, typeA, NULL);
    }
    for (i = 5; i < 10; i++) {
        pthread_create(&tid[i], NULL, typeB, NULL);
    }
    for (i = 0; i < 10; i++) {
        pthread_join(tid[i], NULL);
    }
    printf("Everything finished.\n");
    return 0;
}
